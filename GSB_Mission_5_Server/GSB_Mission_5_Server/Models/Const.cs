using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace GSB_Mission_5_Server.Models
{
    /// <summary>
    /// Classe de déclaration des constantes
    /// </summary>
    public class Const
    {
        public const string server =    "localhost";
        public const string database =  "gsbcrcachan";
        public const string uid =       "root";
        public const string pwd =       "";

        public const string connectionString = "Server="    + server +      ";"
                                             + "Database="  + database +    ";"
                                             + "Uid="       + uid +         ";"
                                             + "Pwd="       + pwd +         ";";
    }
}
