package dao;

import entity.Book;
import entity.Reader;
import entity.Issue;
import dto.IssueDetailsDTO;

import jakarta.persistence.*;
import org.eclipse.persistence.jpa.PersistenceProvider;

import java.util.List;

public class LibraryDAO {

    private static final String ENTITY_MANAGER_FACTORY_NAME = "simpleFactory2";
    private EntityManagerFactory factory;
    private static LibraryDAO instance = null;

    public static LibraryDAO getInstance() {
        if (instance == null) {
            System.out.println("Creating Singleton");
            instance = new LibraryDAO();
        }
        return instance;
    }

    public LibraryDAO() {
        try {
            factory = new PersistenceProvider().createEntityManagerFactory(ENTITY_MANAGER_FACTORY_NAME, null);
            if (factory == null) {
                System.out.print("factory = null");
                throw new IllegalStateException("EntityManagerFactory creation failed. Check persistence.xml.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LibraryDAO(EntityManagerFactory factory) {
        this.factory = factory;
        System.out.println("factory =" + factory);
    }

    // CRUD for Book

    public void createBook(Book book) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = factory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                //entityManager.close();
            }
        }
    }

    public List<Book> loadAllBooks() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            Query query = entityManager.createNamedQuery("findAllBooks");
            List<Book> bookList = (List<Book>)query.getResultList();
            return bookList;
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // CRUD for Reader

    public void createReader(Reader reader) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = factory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(reader);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                //entityManager.close();
            }
        }
    }

    public List<Reader> loadAllReaders() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            Query query = entityManager.createNamedQuery("findAllReaders");
            return query.getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    // CRUD for Issue

    public void createIssue(Issue issue) {
        EntityManager entityManager = null;
        EntityTransaction transaction = null;
        try {
            entityManager = factory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entityManager.persist(issue);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (entityManager != null && entityManager.isOpen()) {
                //entityManager.close();
            }
        }
    }

    public List<Issue> loadAllIssues() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            Query query = entityManager.createNamedQuery("findAllIssues");
            return query.getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<IssueDetailsDTO> loadIssuesWithDetails() {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            TypedQuery<IssueDetailsDTO> query = entityManager.createNamedQuery("findIssuesWithDetails", IssueDetailsDTO.class);
            return query.getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Issue> loadIssuesByReader(Reader reader) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            Query query = entityManager.createNamedQuery("findIssuesByReader");
            query.setParameter("reader", reader);
            return query.getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Issue> loadIssuesByBook(Book book) {
        EntityManager entityManager = null;
        try {
            entityManager = factory.createEntityManager();
            Query query = entityManager.createNamedQuery("findIssuesByBook");
            query.setParameter("book", book);
            return query.getResultList();
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    public List<Issue> findIssuesByReader(Reader reader) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createNamedQuery("findIssuesByReader");
        query.setParameter("reader", reader);
        return query.getResultList();
    }

    public List<Issue> findIssuesByBook(Book book) {
        EntityManager entityManager = factory.createEntityManager();
        Query query = entityManager.createNamedQuery("findIssuesByBook");
        query.setParameter("book", book);
        return query.getResultList();
    }
}
