package ru.kravchenkoei.stocktaking.ui.views;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import ru.kravchenkoei.stocktaking.ui.views.list.*;

@Route("")
public class MainLayout extends AppLayout {

  public MainLayout() {
    createHeader();
    createDrawer();
  }

  private void createHeader() {
    H2 logo = new H2("Учет офисной техники");

    HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);

    header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    header.expand(logo);
    header.setWidth("100%");

    addToNavbar(header);
  }

  private void createDrawer() {
    RouterLink employeeListLink = new RouterLink("Сотрудники", EmployeeListView.class);
    RouterLink companyListLink = new RouterLink("Производители", CompanyListView.class);
    RouterLink elementListLink = new RouterLink("Оборудование", ElementListView.class);
    RouterLink typeListLink = new RouterLink("Вид оборудования", TypeListView.class);
    RouterLink specListLink = new RouterLink("Характеристики", SpecificationListView.class);
    RouterLink locationListLink = new RouterLink("Площадки", LocationListView.class);
    companyListLink.setHighlightCondition(HighlightConditions.sameLocation());

    addToDrawer(
        new VerticalLayout(
            employeeListLink,
            companyListLink,
            elementListLink,
            typeListLink,
            specListLink,
            locationListLink));
  }
}
