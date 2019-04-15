using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GSB_Mission_5_Server.Models
{
    [Table("rapport_visite")]
    public class RapportVisite
    {
        [Key]
        public string vis_matricule { get; set; }
        public int rap_num { get; set; }
        public int pra_num { get; set; }
        public string rap_bilan { get; set; }
        public DateTime rap_dateVisite { get; set; }
        public DateTime rap_dateRapport { get; set; }
    }
}