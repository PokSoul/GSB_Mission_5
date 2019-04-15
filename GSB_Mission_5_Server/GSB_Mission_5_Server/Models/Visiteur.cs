using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GSB_Mission_5_Server.Models
{
    [Table("visiteur")]
    public class Visiteur
    {
        [Key]
        public string vis_matricule { get; set; }
        public string vis_nom { get; set; }
        public string vis_prenom { get; set; }
        public string vis_adresse { get; set; }
        public string vis_cp { get; set; }
        public string vis_ville { get; set; }
        public string vis_login { get; set; }
        public string vis_mdp { get; set; }
    }
}