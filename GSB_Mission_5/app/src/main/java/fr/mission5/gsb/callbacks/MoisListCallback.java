package fr.mission5.gsb.callbacks;

import java.util.List;

import fr.mission5.gsb.objects.Mois;

public interface MoisListCallback {

    void onGetMois(List<Mois> mois);

    void onFailed();

}


