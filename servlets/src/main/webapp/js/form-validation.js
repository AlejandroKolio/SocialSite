$(document).ready(function() {
  jQuery.validator.addMethod("lettersonly", function (value, element) {
    return this.optional(element) || /^[a-z\s]+$/i.test(value);
  });
// Wait for the DOM to be ready
  $(function () {
    // Initialize form validation on the registration form.
    // It has the name attribute "registration"
    $("form[name='registration']").validate({
      // Specify validation rules
      rules: {
        // The key name on the left side is the name attribute
        // of an input field. Validation rules are defined
        // on the right side
        firstname: {
          lettersonly: true
        },
        lastname: {
          lettersonly: true
        },
        email: {
          required: true,
          // Specify that email should be validated
          // by the built-in "email" rule
          email: true
        },
        password: {
          required: true,
          minlength: 8
        }
      },
      // Specify validation error messages
      messages: {
        firstname: {
          lettersonly: "Please enter characters only"
        },
        lastname: {
          lettersonly: "Please enter characters only"
        },
        password: {
          required: "Please provide a password",
          minlength: "Your password must be at least 8 characters long"
        },
        email: "Please enter a valid email address"
      },
      // Make sure the form is submitted to the destination defined
      // in the "action" attribute of the form when valid
      submitHandler: function (form) {
        form.submit();
      }
    });
  });
});