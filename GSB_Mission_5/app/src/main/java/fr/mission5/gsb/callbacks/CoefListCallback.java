package fr.mission5.gsb.callbacks;

import java.util.List;

import fr.mission5.gsb.objects.CoefficientConfiance;

public interface CoefListCallback {

    void onGetListCoef(List<CoefficientConfiance> coeffffs);

    void onFailedCoef();

}
