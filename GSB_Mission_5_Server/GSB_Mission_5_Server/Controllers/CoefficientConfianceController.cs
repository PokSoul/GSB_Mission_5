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
    public class CoefficientConfianceController : ApiController
    {
        private readonly MySqlConnection _connection;

        /// <summary>
        /// Constructeur permettant d'injecter la connexion
        /// </summary>
        public CoefficientConfianceController()
        {
            _connection = new MySqlConnection(Const.connectionString);
        }

        /// <summary>
        /// Retourne tous les coefficients de confiance
        /// </summary>
        /// <returns></returns>
        [HttpGet, Route("coefficientsconfiance")]
        public IHttpActionResult GetCoefficientsConfiance()
        {
            var sql = "SELECT * FROM coefficient_confiance";
            var result = _connection.Query<CoefficientConfiance>(sql).ToList();
            return Ok(result);
        }

        /// <summary>
        /// Retourne le coefficient de confiance dont le numéro est passé en paramètre
        /// </summary>
        /// <param name="coef_num"></param>
        /// <returns></returns>
        [HttpGet, Route("coefficientsconfiance/{coef_num}")]
        public IHttpActionResult GetCoefficientConfiance(string coef_num)
        {
            var sql = "SELECT * FROM coefficient_confiance WHERE coef_num = '" + coef_num + "'";
            var result = _connection.QuerySingle<CoefficientConfiance>(sql);
            return Ok(result);
        }
    }
}