package domain.builder;

import domain.entity.CustomField;
import domain.entity.template.*;

/**
 * This class is designed to integrate builders into the Template framework.
 *
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Oct-2020
 */
public class TemplateEntityBuilder {

    private TemplateEntityBuilder() {
        /*
        Constructor for disable possibility to create instance.
         */
    }

    /**
     * This builder class created for instantiation of {@link Template} objects.
     * You should to use builder for {@link Template} creation.
     * Example:
     * <pre>{@code
     * // ------------
     * Template newTemplate = new Template()
     *          .setId( 1L )
     *          .setName( "Example name" )
     *          .setDescription( "This is example Template object" )
     *          .build();
     * //-------------
     * }</pre>
     *
     * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Oct-2020
     */
    public static class TemplateBuilder {

        private Long id;
        private String name;
        private String description;


        /**
         * Creates an empty builder.
         */
        public TemplateBuilder() {
            /*
             * Empty constructor for creating new empty instance of a class
             */
        }

        /**
         * Creates builder based on exists {@link Template} instance.
         *
         * @param template Instance of {@link Template} which should be used for instantiating of builder.
         */
        public TemplateBuilder( Template template ) {
            id = template.getId();
            name = template.getName();
            description = template.getDescription();
        }


        /**
         * This method creates instance of {@link Template} based on filled data into builder.
         *
         * @return Instance of {@link Template}.
         */
        public Template build() {
            Template template = new Template();
            template.setId( id );
            template.setName( name );
            template.setDescription( description );
            return template;
        }

        /**
         * Sets ID of future {@link Template} object.
         *
         * @param id Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateBuilder setId( Long id ) {
            this.id = id;
            return this;
        }

        /**
         * Sets name of future {@link Template} object.
         *
         * @param name Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateBuilder setName( String name ) {
            this.name = name;
            return this;
        }

        /**
         * Sets description of future {@link Template} object.
         *
         * @param description Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateBuilder setDescription( String description ) {
            this.description = description;
            return this;
        }
    }

    /**
     * This builder class created for instantiation of {@link FieldBlock} objects.
     * You should to use builder for {@link FieldBlock} creation.
     * Example:
     * <pre>{@code
     * // ------------
     * FieldBlock newFieldBlock = new FieldBlockBuilder()
     *          .setId( 1L )
     *          .setName( "Example name" )
     *          .setDescription( "This is example FieldBlock object" )
     *          .build();
     * //-------------
     * }</pre>
     *
     * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Oct-2020
     */
    public static class FieldBlockBuilder {

        private Long id;
        private String name;
        private String description;


        /**
         * Creates an empty builder.
         */
        public FieldBlockBuilder() {
            /*
             * Empty constructor for creating new empty instance of a class
             */
        }

        /**
         * Creates builder based on exists {@link FieldBlock} instance.
         *
         * @param fieldBlock Instance of {@link FieldBlock} which should be used for instantiating of builder.
         */
        public FieldBlockBuilder( FieldBlock fieldBlock ) {
            id = fieldBlock.getId();
            name = fieldBlock.getName();
            description = fieldBlock.getDescription();
        }


        /**
         * This method creates instance of {@link FieldBlock} based on filled data into builder.
         *
         * @return Instance of {@link FieldBlock}.
         */
        public FieldBlock build() {
            FieldBlock fieldBlock = new FieldBlock();
            fieldBlock.setId( id );
            fieldBlock.setName( name );
            fieldBlock.setDescription( description );
            return fieldBlock;
        }

        /**
         * Sets ID of future {@link FieldBlock} object.
         *
         * @param id Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public FieldBlockBuilder setId( Long id ) {
            this.id = id;
            return this;
        }

        /**
         * Sets name of future {@link FieldBlock} object.
         *
         * @param name Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public FieldBlockBuilder setName( String name ) {
            this.name = name;
            return this;
        }

        /**
         * Sets description of future {@link FieldBlock} object.
         *
         * @param description Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public FieldBlockBuilder setDescription( String description ) {
            this.description = description;
            return this;
        }
    }

    /**
     * This builder class created for instantiation of {@link TemplateLayout} objects.
     * You should to use builder for {@link TemplateLayout} creation.
     * Example:
     * <pre>{@code
     * // ------------
     * TemplateLayout newTemplateLayout = new TemplateLayoutBuilder()
     *          .setId( 1L )
     *          .setTemplate( someExistsTemplateObject )
     *          .setFieldBlock( someExistsFieldBlockObject )
     *          .setSequence( 4 )
     *          .build();
     * //-------------
     * }</pre>
     *
     * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Oct-2020
     */
    public static class TemplateLayoutBuilder {

        private Long id;
        private Template template;
        private FieldBlock fieldBlock;
        private Integer sequence;


        /**
         * Creates an empty builder.
         */
        public TemplateLayoutBuilder() {
            /*
             * Empty constructor for creating new empty instance of a class
             */
        }

        /**
         * Creates builder based on exists {@link CustomField} instance.
         *
         * @param templateLayout Instance of {@link CustomField} which should be used for instantiating of builder.
         */
        public TemplateLayoutBuilder( TemplateLayout templateLayout ) {
            id = templateLayout.getId();
            template = templateLayout.getTemplate();
            fieldBlock = templateLayout.getFieldBlock();
            sequence = templateLayout.getSequence();
        }

        /**
         * This method creates instance of {@link TemplateLayout} based on filled data into builder.
         *
         * @return Instance of {@link TemplateLayout}.
         */
        public TemplateLayout build() {
            TemplateLayout templateLayout = new TemplateLayout();
            templateLayout.setId( id );
            templateLayout.setTemplate( template );
            templateLayout.setFieldBlock( fieldBlock );
            templateLayout.setSequence( sequence );
            return templateLayout;
        }

        /**
         * Sets ID of future {@link TemplateLayout} object.
         *
         * @param id Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateLayoutBuilder setId( Long id ) {
            this.id = id;
            return this;
        }

        /**
         * Sets template for the future {@link TemplateLayout} object.
         *
         * @param template Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateLayoutBuilder setTemplate( Template template ) {
            this.template = template;
            return this;
        }

        /**
         * Sets fieldBlock for the future {@link TemplateLayout} object.
         *
         * @param fieldBlock Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateLayoutBuilder setFieldBlock( FieldBlock fieldBlock ) {
            this.fieldBlock = fieldBlock;
            return this;
        }

        /**
         * Sets sequence for the future {@link TemplateLayout} object.
         *
         * @param sequence Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateLayoutBuilder setSequence( Integer sequence ) {
            this.sequence = sequence;
            return this;
        }
    }

    /**
     * This builder class created for instantiation of {@link FieldBlockLayout} objects.
     * You should to use builder for {@link FieldBlockLayout} creation.
     * Example:
     * <pre>{@code
     * // ------------
     * FieldBlockLayout newFieldBlockLayout = new FieldBlockLayoutBuilder()
     *          .setId( 1L )
     *          .setFieldBlock( someExistsFieldBlockObject )
     *          .setCustomField( someExistsCustomFieldObject )
     *          .setSequence( 4 )
     *          .isRequired( false )
     *          .build();
     * //-------------
     * }</pre>
     *
     * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Oct-2020
     */
    public static class FieldBlockLayoutBuilder {

        private Long id;
        private FieldBlock fieldBlock;
        private CustomField customField;
        private Integer sequence;
        private boolean required;


        /**
         * Creates an empty builder.
         */
        public FieldBlockLayoutBuilder() {
            /*
             * Empty constructor for creating new empty instance of a class
             */
        }

        /**
         * Creates builder based on exists {@link FieldBlockLayout} instance.
         *
         * @param fieldBlockLayout Instance of {@link FieldBlockLayout} which should be used for instantiating of builder.
         */
        public FieldBlockLayoutBuilder( FieldBlockLayout fieldBlockLayout ) {
            id = fieldBlockLayout.getId();
            fieldBlock = fieldBlockLayout.getFieldBlock();
            customField = fieldBlockLayout.getCustomField();
            sequence = fieldBlockLayout.getSequence();
            required = fieldBlockLayout.isRequired();
        }


        /**
         * This method creates instance of {@link FieldBlockLayout} based on filled data into builder.
         *
         * @return Instance of {@link FieldBlockLayout}.
         */
        public FieldBlockLayout build() {
            FieldBlockLayout fieldBlockLayout = new FieldBlockLayout();
            fieldBlockLayout.setId( id );
            fieldBlockLayout.setFieldBlock( fieldBlock );
            fieldBlockLayout.setCustomField( customField );
            fieldBlockLayout.setSequence( sequence );
            fieldBlockLayout.setRequired( required );
            return fieldBlockLayout;
        }

        /**
         * Sets ID of future {@link FieldBlockLayout} object.
         *
         * @param id Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public FieldBlockLayoutBuilder setId( Long id ) {
            this.id = id;
            return this;
        }

        /**
         * Sets fieldBlock of future {@link FieldBlockLayout} object.
         *
         * @param fieldBlock Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public FieldBlockLayoutBuilder setFieldBlock( FieldBlock fieldBlock ) {
            this.fieldBlock = fieldBlock;
            return this;
        }

        /**
         * Sets customField of future {@link FieldBlockLayout} object.
         *
         * @param customField Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public FieldBlockLayoutBuilder setCustomField( CustomField customField ) {
            this.customField = customField;
            return this;
        }

        /**
         * Sets sequence of future {@link FieldBlockLayout} object.
         *
         * @param sequence Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public FieldBlockLayoutBuilder setSequence( Integer sequence ) {
            this.sequence = sequence;
            return this;
        }

        /**
         * Sets isRequired of future {@link FieldBlockLayout} object.
         *
         * @param required Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public FieldBlockLayoutBuilder setRequired( Boolean required ) {
            this.required = required;
            return this;
        }
    }

    /**
     * This builder class created for instantiation of {@link TemplateDefaultCustomFieldLayout} objects.
     * You should to use builder for {@link TemplateDefaultCustomFieldLayout} creation.
     * Example:
     * <pre>{@code
     * // ------------
     * TemplateDefaultCustomFieldLayout newTemplateDefaultCustomFieldLayout = new TemplateDefaultCustomFieldLayoutBuilder()
     *          .setId( 1L )
     *          .setFieldBlock( someExistsTemplateObject )
     *          .setCustomField( someExistsCustomFieldObject )
     *          .setSequence( 4 )
     *          .build();
     * //-------------
     * }</pre>
     *
     * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 10-Jul-2021
     */
    public static class TemplateDefaultCustomFieldLayoutBuilder {

        private Long id;
        private Template template;
        private CustomField customField;
        private Integer sequence;


        public TemplateDefaultCustomFieldLayoutBuilder() {
            /*
             * Empty constructor for creating new empty instance of a class
             */
        }

        public TemplateDefaultCustomFieldLayoutBuilder( TemplateDefaultCustomFieldLayout templateDefaultCustomFieldLayout ) {
            id = templateDefaultCustomFieldLayout.getId();
            template = templateDefaultCustomFieldLayout.getTemplate();
            customField = templateDefaultCustomFieldLayout.getCustomField();
            sequence = templateDefaultCustomFieldLayout.getSequence();
        }

        /**
         * This method creates instance of {@link TemplateDefaultCustomFieldLayout} based on filled data into builder.
         *
         * @return Instance of {@link TemplateDefaultCustomFieldLayout}.
         */
        public TemplateDefaultCustomFieldLayout build() {
            TemplateDefaultCustomFieldLayout templateDefaultCustomFieldLayout = new TemplateDefaultCustomFieldLayout();
            templateDefaultCustomFieldLayout.setId( id );
            templateDefaultCustomFieldLayout.setTemplate( template );
            templateDefaultCustomFieldLayout.setCustomField( customField );
            templateDefaultCustomFieldLayout.setSequence( sequence );
            return templateDefaultCustomFieldLayout;
        }

        /**
         * Sets ID of future {@link TemplateDefaultCustomFieldLayout} object.
         *
         * @param id Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateDefaultCustomFieldLayoutBuilder setId( Long id ) {
            this.id = id;
            return this;
        }

        /**
         * Sets template of future {@link TemplateDefaultCustomFieldLayout} object.
         *
         * @param template Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateDefaultCustomFieldLayoutBuilder setTemplate( Template template ) {
            this.template = template;
            return this;
        }

        /**
         * Sets customField of future {@link TemplateDefaultCustomFieldLayout} object.
         *
         * @param customField Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateDefaultCustomFieldLayoutBuilder setCustomField( CustomField customField ) {
            this.customField = customField;
            return this;
        }

        /**
         * Sets sequence of future {@link TemplateDefaultCustomFieldLayout} object.
         *
         * @param sequence Value which should be used.
         *
         * @return Updated instance of builder.
         */
        public TemplateDefaultCustomFieldLayoutBuilder setSequence( Integer sequence ) {
            this.sequence = sequence;
            return this;
        }
    }
}
