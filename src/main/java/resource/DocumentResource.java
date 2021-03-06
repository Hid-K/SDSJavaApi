package resource;

import dto.DocumentDTO;


/**
 * Represents a REST API related with Documents
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 28-Sep-2021
 */
public interface DocumentResource
{

    /**
     * Creates new document.
     * @param assignedTemplates List of templates ID which should be assigned to created document.
     * @return Returns a DTO of Created Document
     */
    DocumentDTO createDocument(Long[] assignedTemplates);

    /**
     * Updates existing document.
     * @param document DTO of the Document with changes (except of ID).
     * @return Returns a DTO of Created Document
     */
    DocumentDTO updateDocument(DocumentDTO document);

    /**
     * Requesting document by it's ID.
     * @param id Id of some specific document.
     * @return Returns a DTO of found Document
     */
    DocumentDTO getDocument(Long id);

    /**
     * Deletes document by ID.
     * @param id Id of some specific document.
     */
    void deleteDocument(Long id);

    /**
     * Providing a list of all documents.
     *
     * @param pageNumber Number of page.
     * @param pageSize Page size.
     * @return List of DTOs
     */
    DocumentDTO[] getAllDocuments(int pageNumber, int pageSize);
}
