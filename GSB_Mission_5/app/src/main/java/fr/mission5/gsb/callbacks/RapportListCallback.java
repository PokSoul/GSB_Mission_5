package fr.mission5.gsb.callbacks;

import java.util.List;

import fr.mission5.gsb.objects.RapportVisite;

public interface RapportListCallback {

    void onRapportListOk(List<RapportVisite> rapports);

}
