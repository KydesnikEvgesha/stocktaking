package ru.kravchenkoei.stocktaking.ui.views.list;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ru.kravchenkoei.stocktaking.service.WorkstationService;
import ru.kravchenkoei.stocktaking.ui.views.MainLayout;

@PageTitle("Рабочие станции | Учет оргтехники")
@Route(value = "/workstation", layout = MainLayout.class)
public class WorkstationListView extends VerticalLayout {
    public static final String VIEW_NAME = "Рабочие станции";

    public final WorkstationService workstationService;

    public WorkstationListView(WorkstationService workstationService){
        this.workstationService = workstationService;

    }

}
