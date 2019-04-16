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
    public class VisiteurController : ApiController
    {
        private readonly MySqlConnection _connection;
        
        public VisiteurController()
        {
            _connection = new MySqlConnection(Const.connectionString);
        }

        /**
         * Retourne tous les visiteurs
         */
        [HttpGet, Route("visiteur")]
        public IHttpActionResult GetVisiteurs()
        {
            var sql = "SELECT * FROM visiteur";
            var result = _connection.Query<Visiteur>(sql).ToList();
            return Ok(result);
        }

        /**
         * Retourne le visiteur dont le matricule est passé en paramètre
         */
        [HttpGet, Route("visiteur/{vis_matricule}")]
        public IHttpActionResult GetVisiteur(string vis_matricule)
        {
            var sql = "SELECT * FROM visiteur WHERE vis_matricule = '" + vis_matricule + "'";
            var result = _connection.QuerySingle<Visiteur>(sql);
            return Ok(result);
        }

        /**
         * Retourne le visiteur dont l'identifiant et le mot de passe sont envoyés par méthode HttpPost
         */
        [HttpPost, Route("visiteur/connexion")]
        public IHttpActionResult PostVisiteur(string login, string mdp)
        {
            var sql = "SELECT * FROM visiteur WHERE vis_login = '" + login + "' AND vis_mdp = '" + mdp + "'";
            var result = _connection.QuerySingle<Visiteur>(sql);
            return Ok(result);
        }

        /**
         * Retourne le visiteur dont l'identifiant et le mot de passe sont en paramètres
         */
        [HttpGet, Route("visiteur/connexion/{login}/{mdp}")]
        public IHttpActionResult GetVisiteur(string login, string mdp)
        {
            var sql = "SELECT * FROM visiteur WHERE vis_login = '" + login + "' AND vis_mdp = '" + mdp + "'";
            var result = _connection.QuerySingle<Visiteur>(sql);
            return Ok(result);
        }
    }
}
