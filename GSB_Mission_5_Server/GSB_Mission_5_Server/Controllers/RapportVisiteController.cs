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
        /// Retourne toutes les dates (yyyy-MM) des rapports de visite d'un visiteur
        /// dont le matricule est passé en paramètre
        /// </summary>
        /// <param name="vis_matricule"></param>
        /// <returns></returns>
        [HttpGet, Route("rapportvisite/visiteur/{vis_matricule}/daterapport")]
        public IHttpActionResult GetMoisRapportsVisite(string vis_matricule)
        {
            var sql = "SELECT DISTINCT SUBSTR(rap_dateRapport, 1, 7) AS rap_moisRapport FROM rapport_visite WHERE vis_matricule = '" + vis_matricule + "'";
            var result = _connection.Query<string>(sql).ToList();
            return Ok(result);
        }

        /// <summary>
        /// Retourne tous les rapports de visite d'un visiteur à une date (yyyy-MM)
        /// dont le matricule de visiteur et la date de rapport sont passés en paramètres
        /// </summary>
        /// <param name="vis_matricule"></param>
        /// <param name="rap_dateRapport"></param>
        /// <returns></returns>
        [HttpGet, Route("rapportvisite/visiteur/{vis_matricule}/daterapport/{rap_dateRapport}/")]
        public IHttpActionResult GetRapportsVisiteDate(string vis_matricule, string rap_dateRapport)
        {
            var sql = "SELECT * FROM rapport_visite WHERE rap_dateRapport LIKE '" + rap_dateRapport + "%' AND vis_matricule = '" + vis_matricule + "'";
            var result = _connection.Query<RapportVisite>(sql).ToList();
            return Ok(result);
        }

        /// <summary>
        /// Retourne le rapports de visites dont le numéro et le matricule de visiteur
        /// sont passés en paramètres
        /// </summary>
        /// <param name="rap_num"></param>
        /// <param name="vis_matricule"></param>
        /// <returns></returns>
        [HttpGet, Route("rapportvisite/{rap_num}/visiteur/{vis_matricule}/")]
        public IHttpActionResult GetRapportsVisite(int rap_num, string vis_matricule)
        {
            var sql = "SELECT * FROM rapport_visite WHERE rap_num = " + rap_num + " AND vis_matricule = '" + vis_matricule + "'";
            var result = _connection.QuerySingle<RapportVisite>(sql);
            return Ok(result);
        }

        /// <summary>
        /// Dans la base de données, insert un nouveau rapport de visite
        /// dont les attributs sont passés en paramètres
        /// </summary>
        /// <param name="vis_matricule"></param>
        /// <param name="pra_num"></param>
        /// <param name="coef_num"></param>
        /// <param name="rap_motif"></param>
        /// <param name="rap_bilan"></param>
        /// <param name="rap_dateVisite"></param>
        /// <returns></returns>
        [HttpPost, Route("rapportvisite/saisie")]
        public IHttpActionResult PostRapportVisite(string vis_matricule,
                                                   int pra_num,
                                                   int coef_num,
                                                   string rap_motif,
                                                   string rap_bilan,
                                                   DateTime rap_dateVisite)
        {
            var sql = @"INSERT INTO rapport_visite (vis_matricule,
                                                    pra_num,
                                                    coef_num,
                                                    rap_motif,
                                                    rap_bilan,
                                                    rap_dateVisite,
                                                    rap_dateRapport)
                        VALUES (@param_vis_matricule,
                                @param_pra_num,
                                @param_coef_num,
                                @param_rap_motif,
                                @param_rap_bilan,
                                @param_rap_dateVisite,
                                CURDATE())";
            try
            {
                var result = _connection.Execute(sql, new
                {
                    param_vis_matricule = vis_matricule,
                    param_pra_num = pra_num,
                    param_coef_num = coef_num,
                    param_rap_motif = rap_motif,
                    param_rap_bilan = rap_bilan,
                    param_rap_dateVisite = rap_dateVisite
                });
                return Ok();
            }
            catch (Exception ex)
            {
                return BadRequest(ex.ToString());
            }
        }
    }
}
