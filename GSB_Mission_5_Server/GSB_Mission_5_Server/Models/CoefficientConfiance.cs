using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GSB_Mission_5_Server.Models
{
    [Table("coefficient_confiance")]
    public class CoefficientConfiance
    {
        [Key]
        public int coef_num { get; set; }
        public string coef_libelle { get; set; }
    }
}