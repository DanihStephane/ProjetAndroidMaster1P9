'use strict';

/* Initialisation du module pg */
const { Client } = require('pg');
const connexFilePath = '../../config/database_config.json';
const connexFile = require.resolve(connexFilePath);

/* Fonction de connexion à la base */
const dbconnect = function () {
  delete require.cache[connexFile];
  const config = require(connexFilePath);
  /* Chaine de connexion à la base */

  return new Client({
    host: config.server,
    port: config.port,
    user: config.username,
    password: config.password,
    database: config.databasename,
    client_encoding: 'utf8'
  });
};

/* Exportation le la fonction de connexion */
module.exports.dbconnect = dbconnect;
