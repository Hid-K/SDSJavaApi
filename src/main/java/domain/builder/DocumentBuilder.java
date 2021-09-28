package domain.builder;

import domain.entity.Document;

import java.util.Date;


/**
 * This builder class created for instantiation of {@link Document} objects.
 * You should to use builder for {@link Document} creation.
 * Example:
 * <pre>{@code
 * // ------------
 * Document newDocument = new DocumentBuilder()
 *          .setId( 1L )
 *          .setCreated( new Date() )
 *          .build();
 * //-------------
 * }</pre>
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Oct-2020
 */
public class DocumentBuilder {

    private Long id;
    private Date created;


    /**
     * Creates an empty builder.
     */
    public DocumentBuilder() {
        /*
         * Empty constructor for creating new empty instance of a class
         */
    }

    /**
     * Creates builder based on exists {@link Document} instance.
     *
     * @param document Instance of {@link Document} which should be used for instantiating of builder.
     */
    public DocumentBuilder( Document document ) {
        id = document.getId();
        created = document.getCreated();
    }


    /**
     * This method creates instance of {@link Document} based on filled data into builder.
     *
     * @return Instance of {@link Document}.
     */
    public Document build() {
        Document document = new Document();
        document.setId( id );
        document.setCreated( created );
        return document;
    }

    /**
     * Sets ID of future {@link Document} object.
     *
     * @param id Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public DocumentBuilder setId( Long id ) {
        this.id = id;
        return this;
    }

    /**
     * Sets CREATED of future {@link Document} object.
     *
     * @param created Value which should be used.
     *
     * @return Updated instance of builder.
     */
    public DocumentBuilder setCreated( Date created ) {
        this.created = created;
        return this;
    }
}
