package edu.iit.sat.itmd4515.mbudhale.lab3;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.HeuristicMixedException;
import jakarta.transaction.HeuristicRollbackException;
import jakarta.transaction.NotSupportedException;
import jakarta.transaction.RollbackException;
import jakarta.transaction.SystemException;
import jakarta.transaction.UserTransaction;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

//using JPA to connect
@WebServlet(urlPatterns = {"/address", "/a", "/addr"})
public class AddressServlet extends HttpServlet {

    @Resource
    Validator validator;

    @Resource(name = "java:app/jdbc/itmd4515DS")
    DataSource ds;
    @PersistenceContext(name = "itmd4515PU")
    EntityManager em;
    @Resource
    UserTransaction tx;

    private static final Logger LOG = Logger.getLogger(AddressServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.fine("things are fine");
        LOG.info("this is a info message");
        LOG.severe("this is a severe message");
        LOG.info("AddessServlet.doPost getting values from my form to my servlet");
        String addressParameter = req.getParameter("address");
        String address2Parameter = req.getParameter("address2");
        String districtParameter = req.getParameter("district");
        String cityIdParameter = req.getParameter("cityId");
        String postalCodeParameter = req.getParameter("postalCode");
        String phoneParameter = req.getParameter("phone");

        Integer cityId = validateAndConvertToInteger(cityIdParameter);
        String address = validateAndGetString(addressParameter);
        String address2 = validateAndGetString(address2Parameter);
        String postalCode = validateAndGetString(postalCodeParameter);
        String district = validateAndGetString(districtParameter);
        String phone = validateAndFormatPhoneNumber(phoneParameter);
        Address a = new Address(address,
                address2,
                district,
                cityId,
                postalCode,
                phone, "POINT(12.3456789 34.5678901)");
        LOG.info(a.toString());
        Set<ConstraintViolation<Address>> violations = validator.validate(a);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Address> v : violations) {
                LOG.info(v.toString());
            }
            req.setAttribute("violations", violations);
            req.setAttribute("address", a);
            RequestDispatcher rd = req.getRequestDispatcher("address.jsp");
            rd.forward(req, resp);
        } else {
            CreateAddressJPA(a);
            req.setAttribute("address", a);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/Confirmation.jsp");
            rd.forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("AddessServlet.doGet redirecting");
        resp.sendRedirect(req.getContextPath() + "/address.jsp");
    }

    private void CreateAddressJPA(Address a) {
        try {
            tx.begin();
            em.persist(a);
            tx.commit();
            
        } catch (NotSupportedException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(AddressServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void CreateAddressJDBC(Address a) {
        String insertAddress = "INSERT INTO address (address, address2, district, city_id, postal_code, phone,location) "
                + "VALUES (?, ?, ?, ?, ?, ?, ST_GeomFromText(?))";
        try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(insertAddress)) {
            ps.setString(1, a.getAddress());
            ps.setString(2, a.getAddress2());
            ps.setString(3, a.getDistrict());
            ps.setInt(4, a.getcityId());
            ps.setString(5, a.getpostalCode());
            ps.setString(6, a.getPhone());
            ps.setString(7, a.getLocation());
            ps.executeUpdate();

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public static Integer validateAndConvertToInteger(String parameter) {
        if (parameter != null && !parameter.isBlank()) {
            try {
                return Integer.valueOf(parameter);
            } catch (NumberFormatException e) {
                LOG.log(Level.SEVERE, parameter);
                return null;
            }
        }
        return null;
    }

    public static String validateAndGetString(String parameter) {
        if (parameter != null && !parameter.isBlank()) {
            try {
                return parameter.trim();
            } catch (Exception e) {
                LOG.log(Level.SEVERE, parameter);
                return null;
            }
        }
        return null;
    }

    public static String validateAndFormatPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isBlank()) {
            String formattedPhoneNumber = phoneNumber.replaceAll("[^0-9]", "");
            if (isValidPhoneNumber(formattedPhoneNumber)) {
                return formattedPhoneNumber;
            } else {
                return null;
            }
        }
        return null;
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{10}");
    }

}
