package validators;

import app.model.Sequence;
import app.validators.SequenceFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import static org.junit.Assert.*;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by msarcevic on 11/21/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class SequenceFormValidatorTest {

    private Errors errors;

    private Sequence sequence;

    @InjectMocks
    private SequenceFormValidator validator;

    @Before
    public void setup(){

        sequence = new Sequence();

        errors = new BindException(sequence, "sequence");

    }

    @Test
    public void validateWithValidSequence(){

        // arrange...
        sequence.setId(5);
        sequence.setAuthor("username");
        sequence.setPurpose("Some purpose");

        // act...
        validator.validate(sequence, errors);

        // assert..
        assertFalse(errors.hasErrors());
    }

    @Test
    public void validateWithInvalidSequence_AuthorNull(){

        // arrange...
        sequence.setId(5);
        sequence.setPurpose("Some purpose");

        // act...
        validator.validate(sequence, errors);

        // assert..
        assertTrue(errors.hasErrors());
        assertThat(errors.getErrorCount()).isEqualTo(1);
        FieldError fieldError = errors.getFieldError("author");
        assertThat(fieldError).isNotNull();
        assertThat(fieldError.getCode()).isEqualTo("sequence.author.empty");
    }

    @Test
    public void validateWithInvalidSequence_AuthorEmpty(){

        // arrange...
        sequence.setId(5);
        sequence.setAuthor("");
        sequence.setPurpose("Some purpose");

        // act...
        validator.validate(sequence, errors);

        // assert..
        assertTrue(errors.hasErrors());
        assertThat(errors.getErrorCount()).isEqualTo(1);
        FieldError fieldError = errors.getFieldError("author");
        assertThat(fieldError).isNotNull();
        assertThat(fieldError.getCode()).isEqualTo("sequence.author.empty");
    }

    @Test
    public void validateWithInvalidSequence_PurposeNull(){

        // arrange...
        sequence.setId(5);
        sequence.setAuthor("username");
        // act...
        validator.validate(sequence, errors);

        // assert..
        assertTrue(errors.hasErrors());
        assertThat(errors.getErrorCount()).isEqualTo(1);
        FieldError fieldError = errors.getFieldError("purpose");
        assertThat(fieldError).isNotNull();
        assertThat(fieldError.getCode()).isEqualTo("sequence.purpose.empty");
    }

    @Test
    public void validateWithInvalidSequence_PurposeEmpty(){

        // arrange...
        sequence.setId(5);
        sequence.setAuthor("username");
        sequence.setPurpose("");
        // act...
        validator.validate(sequence, errors);

        // assert..
        assertTrue(errors.hasErrors());
        assertThat(errors.getErrorCount()).isEqualTo(1);
        FieldError fieldError = errors.getFieldError("purpose");
        assertThat(fieldError).isNotNull();
        assertThat(fieldError.getCode()).isEqualTo("sequence.purpose.empty");
    }

}
