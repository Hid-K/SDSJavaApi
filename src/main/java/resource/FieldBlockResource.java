package resource;

import dto.template.FieldBlockDTO;

import javax.security.auth.login.LoginException;


/**
 * Represents a REST API related with FieldBlocks
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 28-Sep-2021
 */
public interface FieldBlockResource
{
    String endpoint = "/api/fieldBlocks";

    /**
     * Creates a new FieldBlock.
     * @param description Short description for new FieldBlock.
     * @param name Display name of new FieldBlock.
     * @return Returns a DTO of Created FieldBlock
     */
    FieldBlockDTO createFieldBlock( String description, String name) throws LoginException;

    /**
     * Adds CustomField to the FieldBlock.
     * @param customFieldId ID of CustomField which should be added to the FieldBlock.
     * @param fieldBlockId ID of FieldBLock where CustomField should be added.
     * @param position Position of new item in the FieldBlock.
     * @return Returns a DTO of FieldBlock.
     */
    FieldBlockDTO addItemToFieldBlockLayout(Long customFieldId, Long fieldBlockId, int position) throws LoginException;
}
