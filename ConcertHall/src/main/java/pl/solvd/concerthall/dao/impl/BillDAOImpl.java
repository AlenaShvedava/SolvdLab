package pl.solvd.concerthall.dao.impl;

import pl.solvd.concerthall.dao.interfacesDAO.IBillDAO;
import pl.solvd.concerthall.dao.mysql.MySqlDAO;
import pl.solvd.concerthall.entities.Bill;
import pl.solvd.concerthall.utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BillDAOImpl extends MySqlDAO implements IBillDAO {
    Connection c;
    private static final String GET_ALL_BILLS_QUERY = "SELECT * FROM bill";
    private static final String GET_BILL_QUERY = "SELECT * FROM bill WHERE id = ?";
    private static final String INSERT_BILL_QUERY = "INSERT INTO bill (order_id, total_price, payment_status, active) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_BILL_QUERY = "UPDATE bill SET order_id = ?, total_price = ?, payment_status = ?, active = ? WHERE id = ?";
    private static final String DELETE_BILL_QUERY = "DELETE FROM bill WHERE id = ?";

    @Override
    public Bill saveEntity(Bill entity) throws Exception {
        c = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = c.prepareStatement (INSERT_BILL_QUERY)) {
            ps.setLong(1, entity.getOrderId());
            ps.setDouble(2, entity.getTotalPrice());
            ps.setString(3, entity.getPaymentStatus());
            ps.setBoolean(4, entity.isActive());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return entity;
    }

    @Override
    public List<Bill> getAllBill() throws Exception {
        c = ConnectionPool.getInstance().getConnection();
        List<Bill> bill = new ArrayList<>();
        try (PreparedStatement ps = c.prepareStatement(GET_ALL_BILLS_QUERY)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Long orderId = rs.getLong("order_id");
                    Double totalPrice = rs.getDouble("total_price");
                    String paymentStatus = rs.getString("payment_status");
                    Boolean isActive = rs.getBoolean("active");
                    bill.add(new Bill((long) id, orderId, totalPrice, paymentStatus, isActive));
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            } finally {
                try {
                    c.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
            return bill;
        }
    }

    @Override
    public List<Bill> getAllBillBy (Predicate<Bill> predicate) throws Exception {
        c = ConnectionPool.getInstance().getConnection();
        List<Bill> billList = getAllBill();
        billList = billList.stream().filter(predicate).collect(Collectors.toList());
        try {
            c.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return billList;
    }

    @Override
    public void getEntityById(Long id) throws Exception {
        c = ConnectionPool.getInstance().getConnection();
        Bill bill = new Bill();
        try (PreparedStatement ps = c.prepareStatement(GET_BILL_QUERY)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bill.setId(rs.getLong(1));
                    bill.setOrderId(rs.getLong(2));
                    bill.setTotalPrice(rs.getDouble(3));
                    bill.setPaymentStatus(rs.getString(4));
                    bill.setActive(rs.getBoolean(5));
                    System.out.println(bill.getId() + "," + bill.getOrderId() + "," + bill.getTotalPrice() + "," + bill.getPaymentStatus() + "," + bill.isActive());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public List<Bill> updateEntity(Bill entity) throws Exception {
        c = ConnectionPool.getInstance().getConnection();
        List<Bill> updatedBill = new ArrayList<>();
        try (PreparedStatement ps = c.prepareStatement(UPDATE_BILL_QUERY)) {
            ps.setLong(1, entity.getOrderId());
            ps.setDouble(2, entity.getTotalPrice());
            ps.setString(3, entity.getPaymentStatus());
            ps.setBoolean(4, entity.isActive());
            ps.setLong(5, entity.getId());
            ps.executeUpdate();
            updatedBill = getAllBill();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return updatedBill;
    }

    @Override
    public void deleteEntity(Long id) throws Exception {
        c = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = c.prepareStatement(DELETE_BILL_QUERY)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
