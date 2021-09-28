package domain.entity.base;

import java.io.Serializable;


/**
 * @author <a href="mailto:a7l97nn@protonmail.com">Aleksei Laptev</a> on 17-Mar-2020
 */
public interface Identifiable<I extends Serializable> {
    I getId();
}
