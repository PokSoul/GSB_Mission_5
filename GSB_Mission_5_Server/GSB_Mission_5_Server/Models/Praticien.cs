using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GSB_Mission_5_Server.Models
{
    [Table("praticien")]
    public class Praticien
    {
        [Key]
        public int pra_num { get; set; }
        public string pra_nom { get; set; }
        public string pra_prenom { get; set; }
        public string pra_adresse { get; set; }
        public string pra_cp { get; set; }
        public string pra_ville { get; set; }
        public double pra_coefNotoriete { get; set; }
        public string pra_typeCode { get; set; }
        public string pra_visiteur { get; set; }
    }
}