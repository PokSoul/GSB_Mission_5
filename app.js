
const mysql = require('mysql')
const express = require('express')
const bodyParser = require('body-parser');
const config = require('./config/default.json');

const app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json({ type: 'application/*+json' }));
app.listen(62);

console.log("Services OK !");

/**
 * Permet d'obtenir la version du service
 */
app.get('/version', function (req, res) { return res.send({
        error: false,
        data: '1.2.5',
        message: 'GSB Restful API v1.1.0'
    }); });

// Configuration de la connexion à la base de données 'gsb'
const db = mysql.createConnection(config.GSB);
db.connect();

//=================================//
// SERVICES DE LA TABLE "Visiteur" //
//=================================//

/** 
 * connexionVisiteur
 */
app.post('/visiteur/connexion', function(req, res) {

    console.log(req.body);

    // Récuperation des éléments envoyé par le Body
    let vis_login = req.body.vis_login;
    let vis_mdp = req.body.vis_mdp;

    // Requête de verification pour le login vis_login
    db.query('SELECT * FROM visiteur WHERE ?', {vis_login: vis_login}, function(error, results, fields) {
        if (error) throw error;

        // Récuperation du résultat unique
        var result = results[0];

		if(result != null && result.vis_mdp == vis_mdp)
	        {
	            // Envoi l'ensemble des données reçu par la rêquete
	            return res.send(result);
	        }

        // S'il n'y a pas de résultat, visiteur inexistant
        else
        {
            return res.send({
                 error: true,
                 message: "Visiteur inexistant"
            });
        }

    });

});


//==================================//
// SERVICES DE LA TABLE "Praticien" //
//==================================//

/**
 * getPraticienByNum
 */
app.get('/praticien/:pra_num', function(req, res) {

    // Récuperation des parametres
    let pra_num = req.params.pra_num;

    // Création de la requête
    db.query('SELECT * FROM praticien WHERE pra_num = ?', pra_num, function (error, results, fields) {
        if (error) throw error;
        return res.send(results[0]);
    })

});

/**
 * getPraticienByVisMatricule
 */
app.get('/praticien/visiteur/:vis_matricule', function(req, res) {

    // Récuperation des paramètres
    let vis_matricule = req.params.vis_matricule;

    // Création de la requête
    db.query('SELECT * FROM praticien WHERE pra_visiteur = ?', vis_matricule, function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    })

});


//======================================//
// SERVICES DE LA TABLE "RapportVisite" //
//======================================//

/**
 * getRapportVisiteDatesByVisMatricule
 */
app.get('/rapportvisite/visiteur/:vis_matricule/daterapport', function(req, res) {

    // Récuperation des paramètres
    let vis_matricule = req.params.vis_matricule;

    // Création de la requête
    db.query('SELECT DISTINCT SUBSTR(rap_dateRapport, 1, 7) AS rap_moisRapport FROM rapport_visite WHERE vis_matricule = ?', vis_matricule, function (error, results, fields) {
        if (error) throw error;
        return res.send(results);
    })

});

/**
 * getRapportVisiteByVisMatriculeAndDate
 */
app.get('/rapportvisite/visiteur/:vis_matricule/daterapport/:rap_dateRapport/', function(req, res) {

    // Récuperation des paramètres
    let rap_dateRapport = req.params.rap_dateRapport;
    let vis_matricule = req.params.vis_matricule;

    // Création de la requête
    db.query("SELECT * FROM rapport_visite WHERE vis_matricule = ?", vis_matricule, function (error, results, fields) {
        if (error) throw error;
       
        return res.send(results);
    })

});

/**
 * getRapportVisiteByNumAndVisMatricule
 */
app.get('/rapportvisite/:rap_num/visiteur/:vis_matricule/', function(req, res) {

    // Récuperation des paramètres
    let rap_num = req.params.rap_num;
    let vis_matricule = req.params.vis_matricule;

    // Création de la requête
    db.query('SELECT * FROM rapport_visite WHERE rap_num = ?', rap_num, function (error, results, fields) {
        if (error) throw error;

        // Récuperation du résultat unique
        var result = results[0];

        // S'il y a un résultat et que la comparaison est bonne
        if(result.vis_matricule == vis_matricule)
        {
            // Envoi l'ensemble des données reçu par la requête
            return res.send(result);
        }

        // S'il n'y a pas de résultat, matricule inexistant
        else
        {
            return res.send({
                 error: true,
                 message: "Matricule inexistant"
            });
        }

    })

});

/**
 * createRapportVisite
 */
app.post('/rapportvisite/saisie', function(req, res) {

    let vis_matricule = req.body.vis_matricule;
    let pra_num = req.body.pra_num;
    let coef_num = req.body.coef_num;
    let rap_motif = req.body.rap_motif;
    let rap_bilan = req.body.rap_bilan;
    let rap_dateVisite = req.body.rap_dateVisite;

    // Création de la requête
    db.query('INSERT INTO rapport_visite SET ?', { vis_matricule:vis_matricule, pra_num:pra_num, coef_num:coef_num, rap_motif:rap_motif, rap_bilan:rap_bilan, rap_dateVisite:rap_dateVisite}, function (err, rs, flds) {

        if (err) throw err;

        return res.send({
            message: 'Rapport visite OK',
            error: false
        });

    });

 });


//=============================================//
// SERVICES DE LA TABLE "CoefficientConfiance" //
//=============================================//

/**
 * getCoefficientConfiance
 */
app.get('/coefficientconfiance', function(req, res) {
   db.query('SELECT * FROM coefficient_confiance', function (error, results, fields) {
        if (error) throw error;
        console.log(results);
        return res.send(results);
    });});

/**
 * getCoefficientConfianceByNum
 */
app.get('/coefficientconfiance/:coef_num', function(req, res) {

    // Récuperation des paramètres
    let coef_num = req.params.coef_num;

    // Création de la requête
    db.query('SELECT * FROM coefficient_confiance WHERE coef_num = ?', coef_num, function (error, results, fields) {
        if (error) throw error;
        return res.send(results[0]);
    })

});
