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
    /// <summary>
    /// Contrôleur lié aux praticiens
    /// </summary>
    [AllowAnonymous, RoutePrefix("api")]
    public class PraticienController : ApiController
    {
        private readonly MySqlConnection _connection;

        /// <summary>
        /// Constructeur permettant d'injecter la connexion
        /// </summary>
        public PraticienController()
        {
            _connection = new MySqlConnection(Const.connectionString);
        }

        /// <summary>
        /// Retourne tous les praticiens
        /// </summary>
        /// <returns></returns>
        [HttpGet, Route("praticien")]
        public IHttpActionResult GetPraticiens()
        {
            var sql = "SELECT * FROM praticien";
            var result = _connection.Query<Praticien>(sql).ToList();
            return Ok(result);
        }

        /// <summary>
        /// Retourne le praticien dont le numéro est passé en paramètre
        /// </summary>
        /// <param name="pra_num"></param>
        /// <returns></returns>
        [HttpGet, Route("praticien/{pra_num}")]
        public IHttpActionResult GetPraticien(string pra_num)
        {
            var sql = "SELECT * FROM praticien WHERE pra_num = '" + pra_num + "'";
            var result = _connection.QuerySingle<Praticien>(sql);
            return Ok(result);
        }

        /// <summary>
        /// Retoure les praticiens associés à un visiteur dont le matricule est passé en paramètre
        /// </summary>
        /// <param name="vis_matricule"></param>
        /// <returns></returns>
        [HttpGet, Route("praticien/visiteur/{vis_matricule}")]
        public IHttpActionResult GetPraticiensPraVisiteur(string vis_matricule)
        {
            var sql = "SELECT * FROM praticien WHERE pra_visiteur = '" + vis_matricule + "'";
            var result = _connection.Query<Praticien>(sql);
            return Ok(result);
        }
    }
}
