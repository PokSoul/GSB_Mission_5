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
    /// Contrôleur lié aux visiteurs
    /// </summary>
    [AllowAnonymous, RoutePrefix("api")]
    public class VisiteurController : ApiController
    {
        private readonly MySqlConnection _connection;
        
        /// <summary>
        /// Constructeur permettant d'injecter la connexion
        /// </summary>
        public VisiteurController()
        {
            _connection = new MySqlConnection(Const.connectionString);
        }

        /// <summary>
        /// Retourne tous les visiteurs
        /// </summary>
        /// <returns></returns>
        [HttpGet, Route("visiteur")]
        public IHttpActionResult GetVisiteurs()
        {
            var sql = "SELECT * FROM visiteur";
            var result = _connection.Query<Visiteur>(sql).ToList();
            return Ok(result);
        }

        /// <summary>
        /// Retourne le visiteur dont le matricule est passé en paramètre
        /// </summary>
        /// <param name="vis_matricule"></param>
        /// <returns></returns>
        [HttpGet, Route("visiteur/{vis_matricule}")]
        public IHttpActionResult GetVisiteur(string vis_matricule)
        {
            var sql = "SELECT * FROM visiteur WHERE vis_matricule = '" + vis_matricule + "'";
            var result = _connection.QuerySingle<Visiteur>(sql);
            return Ok(result);
        }

        /// <summary>
        /// Retourne le visiteur dont l'identifiant et le mot de passe sont envoyés par méthode HttpPost
        /// </summary>
        /// <param name="login"></param>
        /// <param name="mdp"></param>
        /// <returns></returns>
        [HttpPost, Route("visiteur/connexion")]
        public IHttpActionResult PostVisiteur(string vis_login, string vis_mdp)
        {
            var sql = "SELECT * FROM visiteur WHERE vis_login = '" + vis_login + "' AND vis_mdp = '" + vis_mdp + "'";
            var result = _connection.QuerySingle<Visiteur>(sql);
            return Ok(result);
        }
    }
}
