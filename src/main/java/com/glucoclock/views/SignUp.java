package com.glucoclock.views;
import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Sign up")
@Route(value = "SignUp",layout = MainLayout.class)
public class SignUp extends VerticalLayout{
    private Button PatientButton;
    private Button DoctorButton;
    private Button ResButton;
    private H1 Header ;
    private MenuBar menu = new MenuBar("NS");
    public SignUp(){
        add (menu);
        Header = new H1("Choose your account type:");
        PatientButton = new Button("Patient");
        PatientButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        PatientButton.setWidth("30%");
        DoctorButton = new Button(" Doctor");
        DoctorButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        DoctorButton.setWidth("30%");
        ResButton = new Button(" Researcher");
        ResButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        ResButton.setWidth("30%");


        setMargin(true);
        setHorizontalComponentAlignment(Alignment.CENTER, Header,PatientButton, DoctorButton,ResButton);

        add(Header,PatientButton,DoctorButton,ResButton);
    }
}
