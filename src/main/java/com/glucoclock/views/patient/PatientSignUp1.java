package com.glucoclock.views.patient;

import com.glucoclock.views.MainLayout;
import com.glucoclock.views.MenuBar;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;

@PageTitle("Sign up your patient account")
@Route(value = "PatientSignUp1",layout = MainLayout.class)
public class PatientSignUp1 extends Div {
    TextField firstName;
    TextField lastName;
    EmailField emailField;
    PasswordField password;
    PasswordField confirmPassword;
    FormLayout formLayout;
    Button submitButton;
    VerticalLayout mainLayout;
    private H2 title = new H2("Set up your account");
    private MenuBar menu = new MenuBar("NS");


    public PatientSignUp1() {

        add(menu);
        init();
        HorizontalLayout hl = new HorizontalLayout();
        hl.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

        mainLayout.add(title);
        mainLayout.add(formLayout);
        mainLayout.add(submitButton);

        hl.add(mainLayout);
        add(hl);
    }



    private void init() {
        mainLayoutSetUp();
        firstNameSetUp();
        lastNameSetUp();
        emailFieldSetUp();
        passwordSetUp();
        confirmPasswordSetUp();
        submitButtonSetUp();
        formLayoutSetUp();
    }

    private void mainLayoutSetUp() {
        mainLayout = new VerticalLayout();
        mainLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        mainLayout.setMaxWidth("600px");
        mainLayout.setPadding(false);
    }

    private void submitButtonSetUp() {
        submitButton = new Button("Next", new Icon(VaadinIcon.ARROW_RIGHT));
        submitButton.setIconAfterText(true);
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitButton.getElement().getStyle().set("margin-left", "auto");
        submitButton.addClickListener(e -> {



            if (emailField.isInvalid()) {
                Notification notification = Notification.show("Check Emailfield");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }else if (!password.getValue().equals(confirmPassword.getValue())){
                Notification notification = Notification.show("Check Password");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }else if(firstName.isEmpty()){
                Notification notification = Notification.show("Check First name");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }else if(lastName.isEmpty()){
                Notification notification = Notification.show("Check last name");
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            }
            else {

                VaadinSession.getCurrent().setAttribute( "FirstName",firstName.getValue());
                VaadinSession.getCurrent().setAttribute( "LastName",lastName.getValue());
                VaadinSession.getCurrent().setAttribute( "Email",emailField.getValue());
                VaadinSession.getCurrent().setAttribute( "Password",password.getValue());
                submitButton.getUI().ifPresent(ui ->
                        ui.navigate(PatientSignUp2.class)
                );
            }



        });
    }

    private void formLayoutSetUp() {
        formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                emailField,
                password, confirmPassword
        );
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("320px", 2)
        );
        formLayout.setColspan(firstName,1);
        formLayout.setColspan(lastName, 1);
        formLayout.setColspan(emailField, 2);
        formLayout.setColspan(password, 2);
        formLayout.setColspan(confirmPassword, 2);
    }

    private void firstNameSetUp() {
        firstName = new TextField("First name");
        firstName.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("FirstName")!= null){
            firstName.setValue((String)VaadinSession.getCurrent().getAttribute("FirstName"));
        }
    }

    private void lastNameSetUp() {
        lastName = new TextField("Last name");
        lastName.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("LastName")!= null){
            lastName.setValue((String)VaadinSession.getCurrent().getAttribute("LastName"));
        }
    }

    private void passwordSetUp() {
        password = new PasswordField("Password");
        password.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("Password")!= null){
            password.setValue((String)VaadinSession.getCurrent().getAttribute("Password"));
        }
    }

    private void confirmPasswordSetUp() {
        confirmPassword = new PasswordField("Confirm password");
        confirmPassword.setClearButtonVisible(true);
        if (VaadinSession.getCurrent().getAttribute("Password")!= null){
            confirmPassword.setValue((String)VaadinSession.getCurrent().getAttribute("Password"));
        }
    }

    private void emailFieldSetUp() {
        emailField = new EmailField("Email Address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid example.com email address");
        emailField.setClearButtonVisible(true);
        emailField.setPattern("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        if (VaadinSession.getCurrent().getAttribute("Email")!= null){
            emailField.setValue((String)VaadinSession.getCurrent().getAttribute("Email"));
        }
    }
}
