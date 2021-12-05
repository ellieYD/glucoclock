package com.glucoclock.views.researcher;

import com.glucoclock.views.MainLayout;
import com.glucoclock.views.doctor.DoctorSetting1;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Researcher Settings")
@Route(value = "ResearcherSetting2",layout = MainLayout.class)

public class ResearcherSetting2 extends HorizontalLayout{

    PasswordField oldPassword, newPassword, confirmPassword;
    Button confirmButton, cancelButton;
    VerticalLayout mainLayout;

    public ResearcherSetting2() {
        init();
        setJustifyContentMode(JustifyContentMode.CENTER);

        mainLayout.add(
                oldPassword,
                newPassword,
                confirmPassword,
                new HorizontalLayout(cancelButton, confirmButton)
        );

        add(mainLayout);
    }




    //    Initialize all components
    private void init() {
        mainLayoutInit();
        oldPasswordInit();
        newPasswordInit();
        confirmPasswordInit();
        confirmButtonInit();
        cancelButtonInit();
    }

    //    Following methods are used to initialize a single component
    private void mainLayoutInit() {
        mainLayout = new VerticalLayout();
        mainLayout.setMaxWidth("500px");
        mainLayout.setAlignItems(Alignment.STRETCH);
        mainLayout.setJustifyContentMode(JustifyContentMode.CENTER);
    }

    private void oldPasswordInit() {
        oldPassword = new PasswordField("Current password");
        oldPassword.setClearButtonVisible(true);
        oldPassword.setHelperText("Please enter your current password");
    }

    private void newPasswordInit() {
        newPassword = new PasswordField("New password");
        newPassword.setClearButtonVisible(true);
        newPassword.setHelperText("Please enter your new password");
    }

    private void confirmPasswordInit() {
        confirmPassword = new PasswordField("Confirm you new password");
        confirmPassword.setClearButtonVisible(true);
        confirmPassword.setHelperText("Please enter you new password again");
    }

    private void confirmButtonInit() {
        confirmButton = new Button("Confirm");
        confirmButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        confirmButton.getElement().getStyle().set("margin-left", "auto");
        confirmButton.addClickListener(e ->
                confirmButton.getUI().ifPresent(ui ->
                        ui.navigate(ResearcherSetting1.class)
                )
        );
    }

    private void cancelButtonInit() {
        cancelButton = new Button("Cancel");
        cancelButton.getElement().getStyle().set("margin-right", "auto");
        cancelButton.addClickListener(e ->
                cancelButton.getUI().ifPresent(ui ->
                        ui.navigate(ResearcherSetting1.class)
                )
        );
    }
}

