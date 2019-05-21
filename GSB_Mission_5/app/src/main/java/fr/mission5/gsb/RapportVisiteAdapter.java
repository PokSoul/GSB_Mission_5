package fr.mission5.gsb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.mission5.gsb.objects.RapportVisite;

public class RapportVisiteAdapter extends RecyclerView.Adapter<RapportVisiteAdapter.ViewHolder> {

    private List<RapportVisite> rapportVisites;

    public RapportVisiteAdapter(List<RapportVisite> rapports) {
        this.rapportVisites = rapports;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rapport, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // recupere le rapport de visite courrant de l'item en question
        RapportVisite rapportVisite = rapportVisites.get(position);

        // personnaliser
        // holder.nom.setText()
        // holder.prenom.setText()
        holder.date.setText(rapportVisite.getDateVisite());

        // quand on clique sur les details de chaque item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return rapportVisites.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nom, prenom, date;

        public ViewHolder(View view) {
            super(view);

            this.nom = view.findViewById(R.id.item_rapport_nom);
            this.prenom = view.findViewById(R.id.item_rapport_prenom);
            this.date = view.findViewById(R.id.item_rapport_date);

        }

    }


}
