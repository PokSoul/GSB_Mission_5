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
    public class TypePraticienController : ApiController
    {
        private readonly MySqlConnection _connection;

        public TypePraticienController()
        {
            _connection = new MySqlConnection(Const.connectionString);
        }

        /**
         * Retourne tous les types de praticiens
         */
        [HttpGet, Route("typepraticien")]
        public IHttpActionResult GetTypesPraticien()
        {
            var sql = "SELECT * FROM type_praticien";
            var result = _connection.Query<TypePraticien>(sql).ToList();
            return Ok(result);
        }

        /**
         * Retourne le type de praticien dont le code est passé en paramètre
         */
        [HttpGet, Route("typepraticien/{type_code}")]
        public IHttpActionResult GetTypePraticien(string type_code)
        {
            var sql = "SELECT * FROM type_praticien WHERE type_code = '" + type_code + "'";
            var result = _connection.QuerySingle<TypePraticien>(sql);
            return Ok(result);
        }
    }
}
