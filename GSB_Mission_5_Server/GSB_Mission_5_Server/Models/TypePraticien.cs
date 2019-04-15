using Dapper.Contrib.Extensions;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GSB_Mission_5_Server.Models
{
    [Table("type_praticien")]
    public class TypePraticien
    {
        [Key]
        public string type_code { get; set; }
        public string type_libelle { get; set; }
        public string type_lieu { get; set; }
    }
}