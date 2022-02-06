package ru.kravchenkoei.stocktaking.ui.views.list;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ru.kravchenkoei.stocktaking.data.model.Location;
import ru.kravchenkoei.stocktaking.data.service.LocationService;
import ru.kravchenkoei.stocktaking.ui.views.MainLayout;
import ru.kravchenkoei.stocktaking.ui.views.components.forms.LocationForm;

@PageTitle("Площадки")
@Route(value = "/location", layout = MainLayout.class)
public class LocationListView extends VerticalLayout {

  Grid<Location> locationGrid = new Grid<>();
  TextField filterText = new TextField();
  LocationForm locationForm;
  LocationService locationService;

  public LocationListView(LocationService locationService) {
    this.locationService = locationService;

    setSpacing(false);
    setSizeFull();
    configureGrid();
    configureForm();
    add(getToolbar(), getContent());
    updateList();
    closeEditor();
  }

  private Component getContent() {
    HorizontalLayout content = new HorizontalLayout(locationGrid, locationForm);
    content.setFlexGrow(2, locationGrid);
    content.setFlexGrow(1, locationForm);
    content.addClassNames("content");
    content.setSizeFull();
    return content;
  }

  private void configureForm() {
    locationForm = new LocationForm();
    locationForm.setWidth("25em");
    locationForm.addListener(LocationForm.SaveEvent.class, this::saveLocation);
    locationForm.addListener(LocationForm.DeleteEvent.class, this::deleteLocation);
    locationForm.addListener(LocationForm.CloseEvent.class, e -> closeEditor());
  }

  private void saveLocation(LocationForm.SaveEvent event) {
    locationService.saveLocation(event.getLocation());
    updateList();
    closeEditor();
  }

  private void deleteLocation(LocationForm.DeleteEvent event) {
    locationService.deleteLocation(event.getLocation().getId());
    updateList();
    closeEditor();
  }

  public void editLocation(Location location) {
    if (location == null) {
      closeEditor();
    } else {
      locationForm.setLocation(location);
      locationForm.setVisible(true);
      addClassName("editing");
    }
  }

  private void closeEditor() {
    locationForm.setLocation(null);
    locationForm.setVisible(false);
    removeClassName("editing");
  }

  private void addCompany() {
    locationGrid.asSingleSelect().clear();
    editLocation(new Location());
  }

  public void configureGrid() {
    locationGrid.addClassName("contact-grid");
    locationGrid.setSizeFull();
    locationGrid.addColumn(Location::getNameOffice).setHeader("Наименование площадки");
    locationGrid.addColumn(Location::getAddress).setHeader("Адрес");
    locationGrid.getColumns().forEach(col -> col.setAutoWidth(true));

    locationGrid.asSingleSelect().addValueChangeListener(event -> editLocation(event.getValue()));
  }

  private HorizontalLayout getToolbar() {
    filterText.setPlaceholder("Фильтр по имени...");
    filterText.setClearButtonVisible(true);
    filterText.setValueChangeMode(ValueChangeMode.LAZY);
    filterText.addValueChangeListener(e -> updateList());

    Button addCompanyButton = new Button("Добавить площадку");
    addCompanyButton.addClickListener(click -> addCompany());

    HorizontalLayout toolbar = new HorizontalLayout(filterText, addCompanyButton);
    toolbar.addClassName("toolbar");
    return toolbar;
  }

  private void updateList() {
    locationGrid.setItems(locationService.searchLocations(filterText.getValue().trim()));
  }
}
