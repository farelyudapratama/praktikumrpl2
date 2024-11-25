package datamahasiswa;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControllerMahasiswa {
    private SessionFactory sessionFactory;
    private DefaultTableModel tabelModel;

    public ControllerMahasiswa() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void insertData(String npm, String nama, int tinggi, boolean pindahan) {
        String pindahanText = pindahan ? "Ya" : "Tidak";
        ModelMahasiswa mhs = new ModelMahasiswa(npm, nama, tinggi, pindahanText);

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(mhs);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateData(String npm, String nama, int tinggi, boolean pindahan) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ModelMahasiswa mhs = session.get(ModelMahasiswa.class, npm);
            if (mhs == null) {
                throw new Exception("Data tidak ditemukan.");
            }
            mhs.setNama(nama);
            mhs.setTinggi(tinggi);
            mhs.setPindahan(pindahan);
            session.update(mhs);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void deleteData(String npm) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            ModelMahasiswa mhs = session.get(ModelMahasiswa.class, npm);
            if (mhs == null) {
                throw new Exception("Data tidak ditemukan.");
            }
            session.delete(mhs);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public DefaultTableModel showData() {
        String[] kolom = {"NPM", "Nama", "Tinggi", "Pindahan"};
        Session session = sessionFactory.openSession();

        try {
            List<ModelMahasiswa> mahasiswaList = session.createQuery("from ModelMahasiswa", ModelMahasiswa.class).list();
            Object[][] objData = new Object[mahasiswaList.size()][4];
            for (int i = 0; i < mahasiswaList.size(); i++) {
                ModelMahasiswa mhs = mahasiswaList.get(i);
                objData[i] = new Object[]{
                    mhs.getNPM(),
                    mhs.getNama(),
                    mhs.getTinggi(),
                    mhs.isPindahan() ? "Ya" : "Tidak"
                };
            }

            tabelModel = new DefaultTableModel(objData, kolom) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            return tabelModel;
        } finally {
            session.close();
        }
    }
}
