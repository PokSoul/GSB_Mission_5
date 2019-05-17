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
    /// Contrôleur lié aux rapports de visites (comptes rendus)
    /// </summary>
    [AllowAnonymous, RoutePrefix("api")]
    public class RapportVisiteController : ApiController
    {
        private readonly MySqlConnection _connection;

        /// <summary>
        /// Constructeur permettant d'injecter la connexion
        /// </summary>
        public RapportVisiteController()
        {
            _connection = new MySqlConnection(Const.connectionString);
        }

        /// <summary>
        /// Retourne tous les rapports de visites
        /// </summary>
        /// <returns></returns>
        [HttpGet, Route("rapportvisite")]
        public IHttpActionResult GetRapportsVisite()
        {
            var sql = "SELECT * FROM rapport_visite";
            var result = _connection.Query<RapportVisite>(sql).ToList();
            return Ok(result);
        }

        /// <summary>
        /// Retourne toutes les dates (format année-mois) des rapports de visite d'un visiteur
        /// dont le matricule est passé en paramètre
        /// </summary>
        /// <param name="vis_matricule"></param>
        /// <returns></returns>
        [HttpGet, Route("rapportvisite/{vis_matricule}")]
        public IHttpActionResult GetRapportVisite(string vis_matricule)
        {
            var sql = "SELECT DISTINCT SUBSTR(rap_dateRapport, 1, 7) AS rap_moisRapport FROM rapport_visite WHERE vis_matricule = '" + vis_matricule + "'";
            var result = _connection.Query<string>(sql).ToList();
            return Ok(result);
        }

        /// <summary>
        /// Retourne tous les rapports de visite d'un visiteur à une date, tout deux passés en paramètres
        /// </summary>
        /// <param name="rap_dateRapport"></param>
        /// <param name="vis_matricule"></param>
        /// <returns></returns>
        [HttpGet, Route("rapportvisite/{vis_matricule}/{rap_dateRapport}/")]
        public IHttpActionResult GetRapportsVisite(string rap_dateRapport, string vis_matricule)
        {
            var sql = "SELECT * FROM rapport_visite WHERE rap_dateRapport LIKE '" + rap_dateRapport + "%' AND vis_matricule = '" + vis_matricule + "'";
            var result = _connection.Query<RapportVisite>(sql).ToList();
            return Ok(result);
        }      
    }
}
