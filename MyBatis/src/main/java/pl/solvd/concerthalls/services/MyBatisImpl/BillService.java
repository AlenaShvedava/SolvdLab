package pl.solvd.concerthalls.services.MyBatisImpl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.DAO.interfaces.IBillDAO;
import pl.solvd.concerthalls.binary.Bill;
import pl.solvd.concerthalls.services.interfaces.IBillService;
import pl.solvd.concerthalls.utils.MyBatisFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BillService implements IBillService {
    Logger LOG = LogManager.getLogger(BillService.class);

    private static final SqlSessionFactory SESSION_FACTORY = MyBatisFactory.getSqlSessionFactory();

    @Override
    public Bill addBill(Bill bill) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IBillDAO authorsDAO = session.getMapper(IBillDAO.class);
            try {
                authorsDAO.addEntity(bill);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return bill;
    }

    @Override
    public List<Bill> getAllBillsBy(Predicate<Bill> predicate) {
        return null;
    }

    @Override
    public Bill getBillById(Long billId) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IBillDAO billDAO = session.getMapper(IBillDAO.class);
            Bill bill = billDAO.getEntityById(billId);
            return bill;
        }
    }

    @Override
    public List<Bill> updateBill(Bill bill) {
        List<Bill> updatedBill = new ArrayList<>();
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IBillDAO billDAO = session.getMapper(IBillDAO.class);
            try {
                billDAO.updateEntity(bill);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return updatedBill;
    }

    @Override
    public void deleteBill(Long id) {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IBillDAO billDAO = session.getMapper(IBillDAO.class);
            try {
                billDAO.deleteEntity(id);
                session.commit();
            } catch (Exception e) {
                session.rollback();
                LOG.error(e.getMessage(), e);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Bill> getAll() {
        try (SqlSession session = SESSION_FACTORY.openSession()) {
            IBillDAO billDAO = session.getMapper(IBillDAO.class);
            List<Bill> list = billDAO.getAll();
            return list;
        }
    }
}
