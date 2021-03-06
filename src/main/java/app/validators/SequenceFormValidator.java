package app.validators;

import app.model.Sequence;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by msarcevic on 11/21/16.
 */
@Component
public class SequenceFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(Sequence.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        Sequence sequence = (Sequence) object;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "author", "sequence.author.empty", "Field \"Author\" cannot be empty!");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "purpose", "sequence.purpose.empty", "Field \"Purpose\" cannot be empty!");
    }

}
