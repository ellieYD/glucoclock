package com.glucoclock.views.patient;



import com.glucoclock.database.patients_db.model.Patient;
import com.glucoclock.database.patients_db.service.PatientService;
import com.glucoclock.security.db.UserService;
import com.glucoclock.views.components.MenuBar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.UUID;


@PageTitle("Settings")
@Route(value = "patient/settings")
public class PatientSettings1View extends HorizontalLayout {


    //     All variables
    private String FName;
    private String LName;
    private String Email;
    private String AddressL1;
    private String AddressL2;
    private String PostCode;
    private String City;
    private String Phone;
    private LocalDate Birth;
    private String Gender;
    private String Diabetes;
    private boolean rapidInsulin, shortInsulin, intermediateInsulin, longInsulin;;
    private String injection;

//    All Components on the page
    private H1 space = new H1("  ");
    private TextField firstName;
    private TextField lastName;
    private DatePicker datePicker;
    private EmailField emailField;
    private TextField homeAddressL1;
    private TextField homeAddressL2;
    private TextField postcode;
    private TextField cityField;
    private TextField contactNumber;
    private Select<String> genderSelect;
    private Select<String> diabetesSelect;
    private Select<String> injectionSelect;
    private CheckboxGroup<String> insulinSelect;

    private Button changeSetting, save, cancel, changePassword;
    private MenuBar menu = new MenuBar("PNS");

    private VerticalLayout MainLayout = new VerticalLayout();
    private HorizontalLayout Buttons = new HorizontalLayout();

    private final PatientService patientService;
    private final UserService userService;

    public PatientSettings1View(UserService userService, PatientService patientService) {

        this.patientService = patientService;
        this.userService = userService;

//        The uid of current user
        UUID uid = userService.getRepository().findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getUid();
        init(patientService.getRepository().getPatientByUid(uid)); // initialize the components on the page

//        Setting the layout of the page
        FormLayout formLayout = new FormLayout();
        formLayout.add(
                firstName, lastName,
                datePicker, genderSelect,
                emailField, contactNumber,
                homeAddressL1,
                homeAddressL2
        );
        formLayout.setResponsiveSteps(
                // Use one column by default
                new FormLayout.ResponsiveStep("0", 1),
                // Use two columns, if the layout's width exceeds 320px
                new FormLayout.ResponsiveStep("300px", 2)
        );
        formLayout.setColspan(firstName,1);
        formLayout.setColspan(lastName,1);
        formLayout.setColspan(datePicker,1 );
        formLayout.setColspan(emailField,1 );
        formLayout.setColspan(homeAddressL1,2 );
        formLayout.setColspan(homeAddressL2,2 );
        formLayout.setColspan(contactNumber,1 );
        formLayout.setColspan(genderSelect,1 );

        Buttons.setWidth(MainLayout.getWidth());
        Buttons.add(changePassword,changeSetting, save, cancel);



////        -------------Temporary button for testing------------
//        Button button = new Button("Check this");
//        button.addClickListener(e ->{
//            patientService.bulkcreate();
//                }
//        );
////        -----------------------------------------------------


        MainLayout.add(
                space,
                new H2("Settings"),
                Buttons,
                formLayout,
                postcode, cityField,
                diabetesSelect,
                insulinSelect,
                injectionSelect
                //toHome
                //button // ← button for testing
        );
        MainLayout.setMaxWidth("600px");
        MainLayout.setPadding(false);

        add(
                menu,
                MainLayout
        );

//        set all components in the middle of the page (horizontally)
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);

    }


//  Initialize the elements in the page
    private void init(Patient patient) {

//      set the values of the variables
        FName = patient.getFirstName();
        LName = patient.getLastName();
        Email = patient.getEmail();
        PostCode = patient.getPostCode();
        AddressL1 = patient.getHomeAddressL1();
        AddressL2 = patient.getHomeAddressL2();
        City = patient.getCity();
        Gender = patient.getGender();
        Phone = patient.getPhone();
        Birth = patient.getBirthday();
        Diabetes = patient.getDiabetesType();
        injection = patient.getInjectionMethod();
        rapidInsulin = patient.isRapidInsulin();
        shortInsulin = patient.isShortInsulin();
        intermediateInsulin = patient.isIntermediateInsulin();
        longInsulin = patient.isLongInsulin();


//      Initialize the components
        firstNameSetUp();
        lastNameSetUp();
        datePickerSetUp();
        emailFieldSetUp();
        homeAddressL1SetUp();
        homeAddressL2SetUp();
        postcodeSetUp();
        cityFieldSetUp();
        contactNumberSetUp();
        genderSelectSetUp();
        diabetesSelectSetUp();
        insulinSelectSetUp();
        injectionSelectSetUp();
        changeSettingSetUp();
        saveSetUp(patient.getUid());
        cancelSetUp();
        changePasswordSetUp();
        //toHomeSetUp();

    }


//    Following functions are used to initialize all the components
//    Text field of first name
    private void firstNameSetUp(){
        firstName = new TextField("First name");
        firstName.setValue(FName);
        firstName.setClearButtonVisible(true);
        firstName.setReadOnly(true);

    }

//  Text field of last name
    private void lastNameSetUp() {
        lastName = new TextField("Last name");
        lastName.setValue(LName);
        lastName.setClearButtonVisible(true);
        lastName.setReadOnly(true);
    }

//    Text field of phone number
    private void contactNumberSetUp() {
        contactNumber = new TextField();
        contactNumber.setLabel("Phone number");
        contactNumber.setHelperText("Include country and area prefixes");
        contactNumber.setValue(Phone);
        contactNumber.setClearButtonVisible(true);
        contactNumber.setReadOnly(true);
    }

//    Email field of email
    private void emailFieldSetUp() {
        emailField = new EmailField();
        emailField.setLabel("Email address");
        emailField.getElement().setAttribute("name", "email");
        emailField.setPlaceholder("username@example.com");
        emailField.setErrorMessage("Please enter a valid example.com email address");
        emailField.setClearButtonVisible(true);
        emailField.setPattern("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        emailField.setValue(Email);
        emailField.setReadOnly(true);
    }

//    Date picker of birthday
    private void datePickerSetUp() {
        datePicker = new DatePicker("Date of birth");
        LocalDate now = LocalDate.now(ZoneId.systemDefault()).minusYears(7);
        datePicker.setMax(now);
        datePicker.setMin(now.minusYears(120));
        datePicker.setHelperText("You must be a minimum of 7 years old");
        datePicker.setHelperText("Format: DD.MM.YYYY");
        datePicker.setPlaceholder("DD.MM.YYYY");
        datePicker.setValue(Birth);
        datePicker.setReadOnly(true);
    }

//    Text field of address line 1
    private void homeAddressL1SetUp() {
        homeAddressL1 = new TextField("Address Line1");
        homeAddressL1.setValue(AddressL1);
        homeAddressL1.setClearButtonVisible(true);
        homeAddressL1.setReadOnly(true);
    }

//    Text field of address line 2
    private void homeAddressL2SetUp() {
        homeAddressL2 = new TextField("Address Line2");
        homeAddressL2.setValue(AddressL2);
        homeAddressL2.setClearButtonVisible(true);
        homeAddressL2.setReadOnly(true);
    }

//    Text field of postcode
    private void postcodeSetUp() {
        postcode = new TextField("Postcode");
        postcode.setValue(PostCode);
        postcode.setClearButtonVisible(true);
        postcode.setReadOnly(true);
    }

//    Text field of city
    private void cityFieldSetUp() {
        cityField = new TextField("City");
        cityField.setValue(City);
        cityField.setClearButtonVisible(true);
        cityField.setReadOnly(true);
    }

//    Selection of gender
    private void genderSelectSetUp() {
        genderSelect = new Select<>("Male","Female","Others","Prefer not to say");
        genderSelect.setValue(Gender);
        genderSelect.setLabel("Gender");
        genderSelect.setReadOnly(true);
    }

//    Selection of type of diabetes
    private void diabetesSelectSetUp() {
        diabetesSelect = new Select<>("Type I","Type II","Gestational");
        diabetesSelect.setValue(Diabetes);
        diabetesSelect.setLabel("Type of diabetes");
        diabetesSelect.setReadOnly(true);
    }

//    Checkbox of insulin type
    private void insulinSelectSetUp() {
        insulinSelect = new CheckboxGroup<>();
        insulinSelect.setLabel("Insulin type");
        insulinSelect.setItems("Rapid-acting insulin","Short-acting insulin","Intermediate-acting insulin","Long-acting insulin");
        insulinSelect.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);

        if (rapidInsulin)
            insulinSelect.select("Rapid-acting insulin");
        if (shortInsulin)
            insulinSelect.select("Short-acting insulin");
        if (intermediateInsulin)
            insulinSelect.select("Intermediate-acting insulin");
        if (longInsulin)
            insulinSelect.select("Long-acting insulin");

        insulinSelect.setReadOnly(true);
    }

//    Selection of injection method of insulin
    private void injectionSelectSetUp() {
        injectionSelect = new Select<>();
        injectionSelect.setLabel("Injection method");
        injectionSelect.setItems("Syringe", "Injection pen", "Insulin pump");
        injectionSelect.setValue(injection);
        injectionSelect.setReadOnly(true);
    }

//    Button to change info
    private void changeSettingSetUp() {
        changeSetting = new Button("Edit Info");
        changeSetting.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        changeSetting.getElement().getStyle().set("margin-left", "auto");
        changeSetting.addClickListener(e -> {
            allSetReadOnly(false);
            changeSetting.setVisible(false);
            changePassword.setVisible(false);
            save.setVisible(true);
            cancel.setVisible(true);
        });

    }

//    button to save changes
    private void saveSetUp(UUID uid) {
        save = new Button("Save");
        save.setVisible(false);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-left", "1em");

        save.addClickListener(e -> {
            if(firstName.isEmpty() || lastName.isEmpty() || emailField.isEmpty() || emailField.isInvalid() || genderSelect.isEmpty() || homeAddressL1.isEmpty() || postcode.isEmpty() || cityField.isEmpty() || contactNumber.isEmpty() || datePicker.isEmpty() || diabetesSelect.isEmpty() || insulinSelect.isEmpty() || injectionSelect.isEmpty()) {
                //Show the error messages
                if (firstName.isEmpty()){
                    Notification notification = Notification.show("First name is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (lastName.isEmpty()){
                    Notification notification = Notification.show("Last name is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (emailField.isEmpty() || emailField.isInvalid()){
                    Notification notification = Notification.show("A valid email is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (genderSelect.isEmpty()){
                    Notification notification = Notification.show("Gender is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (homeAddressL1.isEmpty()){
                    Notification notification = Notification.show("Your address is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (postcode.isEmpty()){
                    Notification notification = Notification.show("Postcode is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (cityField.isEmpty()){
                    Notification notification = Notification.show("City is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (contactNumber.isEmpty()){
                    Notification notification = Notification.show("Phone number is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (datePicker.isEmpty()){
                    Notification notification = Notification.show("Birthday is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (diabetesSelect.isEmpty()){
                    Notification notification = Notification.show("Your diabetes type is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (insulinSelect.isEmpty()){
                    Notification notification = Notification.show("Your insulin type is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

                if (injectionSelect.isEmpty()){
                    Notification notification = Notification.show("Your injection method of insulin is required", 3000, Notification.Position.TOP_CENTER);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                }

            } else {
//            update the values of the variables
                FName = firstName.getValue();
                LName = lastName.getValue();
                Email = emailField.getValue();
                AddressL1 = homeAddressL1.getValue();
                AddressL2 = homeAddressL2.getValue();
                PostCode = postcode.getValue();
                City = cityField.getValue();
                Phone = contactNumber.getValue();
                Birth = datePicker.getValue();
                Gender = genderSelect.getValue();
                Diabetes = diabetesSelect.getValue();
                rapidInsulin = insulinSelect.isSelected("Rapid-acting insulin");
                shortInsulin = insulinSelect.isSelected("Short-acting insulin");
                intermediateInsulin = insulinSelect.isSelected("Intermediate-acting insulin");
                longInsulin = insulinSelect.isSelected("Long-acting insulin");
                injection = injectionSelect.getValue();

//            update any changes to the database
                patientService.updatePatientFirstName(uid,FName);
                patientService.updatePatientLastName(uid, LName);
                patientService.updatePatientEmail(uid, Email);
                patientService.updatePatientAddressL1(uid, AddressL1);
                patientService.updatePatientAddressL2(uid, AddressL2);
                patientService.updatePatientPostCode(uid, PostCode);
                patientService.updatePatientCity(uid, City);
                patientService.updatePatientPhone(uid, Phone);
                patientService.updatePatientBirthday(uid, Birth);
                patientService.updatePatientGender(uid, Gender);
                patientService.updateInsulinType(uid, rapidInsulin, shortInsulin, intermediateInsulin, longInsulin);
                patientService.updateDiabetesType(uid, Diabetes);
                patientService.updateInjectionMethod(uid, injection);

//            Change the accessibility and appearance when saved
                allSetReadOnly(true);
                changeSetting.setVisible(true);
                changePassword.setVisible(true);
                save.setVisible(false);
                cancel.setVisible(false);


                Notification.show("Changes saved",2000, Notification.Position.TOP_CENTER);
            }
        });
    }

//    button to cancel the changes
    private void cancelSetUp() {
        cancel = new Button("Cancel");
        cancel.setVisible(false);
        cancel.getElement().getStyle().set("margin-right", "0");
        cancel.addClickListener(e -> {
//            Rollback the values in the components
            firstName.setValue(FName);
            lastName.setValue(LName);
            datePicker.setValue(Birth);
            emailField.setValue(Email);
            homeAddressL1.setValue(AddressL1);
            homeAddressL2.setValue(AddressL2);
            postcode.setValue(PostCode);
            cityField.setValue(City);
            contactNumber.setValue(Phone);
            genderSelect.setValue(Gender);
            diabetesSelect.setValue(Diabetes);
            insulinSelect.deselectAll();
            if (rapidInsulin)
                insulinSelect.select("Rapid-acting insulin");
            if (shortInsulin)
                insulinSelect.select("Short-acting insulin");
            if (intermediateInsulin)
                insulinSelect.select("Intermediate-acting insulin");
            if (longInsulin)
                insulinSelect.select("Long-acting insulin");
            injectionSelect.setValue(injection);

//            Change the accessibility and appearance when cancelled
            changeSetting.setVisible(true);
            changePassword.setVisible(true);
            save.setVisible(false);
            cancel.setVisible(false);
            allSetReadOnly(true);
            Notification.show("Changes cancelled",2000, Notification.Position.TOP_CENTER);
        });
    }

//    Button to change password
    private void changePasswordSetUp() {
        changePassword = new Button("Change Password");
        changePassword.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        save.getElement().getStyle().set("margin-right", "auto");
        changePassword.addClickListener(e ->
                changePassword.getUI().ifPresent(ui ->
                        ui.navigate(PatientSettings2View.class)
                )
        );
    }

////    Button to go back to home page
//    private void toHomeSetUp() {
//        toHome = new Button("OK");
//        toHome.getElement().getStyle().set("margin-left", "auto");
//        toHome.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        toHome.addClickListener(e -> {
//            toHome.getUI().ifPresent(ui ->
//                    ui.navigate(PatientStartView.class)
//            );
//        });
//    }


//    Set all the components on the page to be read-only or not
    private void allSetReadOnly(boolean Boolean) {
        firstName.setReadOnly(Boolean);
        lastName.setReadOnly(Boolean);
        datePicker.setReadOnly(Boolean);
        homeAddressL1.setReadOnly(Boolean);
        homeAddressL2.setReadOnly(Boolean);
        postcode.setReadOnly(Boolean);
        cityField.setReadOnly(Boolean);
        contactNumber.setReadOnly(Boolean);
        genderSelect.setReadOnly(Boolean);
        diabetesSelect.setReadOnly(Boolean);
        insulinSelect.setReadOnly(Boolean);
        injectionSelect.setReadOnly(Boolean);
//        toHome.setEnabled(Boolean);
    }
}