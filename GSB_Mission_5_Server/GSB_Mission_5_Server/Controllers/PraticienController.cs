using Dapper;
using GSB_Mission_5_Server.Models;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace GSB_Mission_5_Server.Controllers
{
    [AllowAnonymous, RoutePrefix("api")]
    public class PraticienController : ApiController
    {
        private readonly MySqlConnection _connection;

        public PraticienController()
        {
            _connection = new MySqlConnection(Const.connectionString);
        }

        /**
         * Retourne tous les praticiens
         */
        [HttpGet, Route("praticien")]
        public IHttpActionResult GetPraticiens()
        {
            var sql = "SELECT * FROM praticien";
            var result = _connection.Query<Praticien>(sql).ToList();
            return Ok(result);
        }

        /**
         * Retourne le praticien dont le numéro est passé en paramètre
         */
        [HttpGet, Route("praticien/{pra_num}")]
        public IHttpActionResult GetPraticien(string pra_num)
        {
            var sql = "SELECT * FROM praticien WHERE pra_num = '" + pra_num + "'";
            var result = _connection.QuerySingle<Praticien>(sql);
            return Ok(result);
        }

        /**
         * Retoure les praticiens associés à un visiteur dont le matricule est passé en paramètre
         *
        [HttpGet, Route("praticien/{pra_visiteur}")]
        public IHttpActionResult GetPraticiensPraVisiteur(string pra_visiteur)
        {
            var sql = "SELECT * FROM praticien WHERE pra_visiteur = '" + pra_visiteur + "'";
            var result = _connection.Query<Praticien>(sql);
            return Ok(result);
        }
        */
    }
}
