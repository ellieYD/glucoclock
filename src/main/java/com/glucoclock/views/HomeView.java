package com.glucoclock.views;

import com.glucoclock.views.MainLayout;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Home")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

//    private TextField name;
//    private Button sayHello;
    private Button signInBut;
    private Button signUpBut;
    //private Text welcomeTxt = new Text("-Your No.1 choice of digital diabetic logbook-");

    public HomeView() {
        //Logo
        Image logo = new Image("images/GC_logo.png","logo");
        logo.setWidth("50%");
        //Sign in button
        signInBut = new Button("Sign In");
        signInBut.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        signInBut.setWidth("30%");
        signInBut.setHeight("50px");
        //Sign up button
        signUpBut = new Button("Sign Up");
        signUpBut.setWidth("30%");
        signUpBut.setHeight("50px");


        setMargin(true);
        add(logo,signInBut,signUpBut);
        setHorizontalComponentAlignment(Alignment.CENTER, logo,signInBut, signUpBut);

    }

}
