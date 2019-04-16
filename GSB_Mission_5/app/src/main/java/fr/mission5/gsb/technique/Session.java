package fr.mission5.gsb.technique;

import fr.mission5.gsb.metier.Visiteur;

public class Session {
        private static Session session = null;
        private Visiteur leVisiteur ;

        private Session (Visiteur leVisiteur)
        {
            super();
            this.leVisiteur = leVisiteur;
        }

        public static boolean init(Visiteur visiteur)  {
            boolean res = false;
            System.out.println("init");
            if (visiteur != null) {
                Session.session = new Session(visiteur);
                return true;
            } else {
                return false;
            }
            //return res;
        }

   /*public static boolean init(JSONObject response, Visiteur visiteur) {
       if ((response != null) && (visiteur != null)) {
           Session.session = new Session(visiteur);
           return true;
       } else {
           return false;
       }
   }*/



        public static Session getSession() {
            return Session.session;
        }

        public void fermer() {
            Session.session = null;
        }

        public Visiteur getLeVisiteur() {
            return this.leVisiteur;
        }
}


