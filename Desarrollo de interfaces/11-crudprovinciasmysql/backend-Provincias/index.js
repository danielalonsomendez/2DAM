const express = require('express');
const mysql = require('mysql2');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(bodyParser.json());

// Configurar la conexión a la base de datos MySQL
const db = mysql.createConnection({
    host: 'localhost', // Dirección del servidor MySQL
    user: 'root', // Usuario de MySQL
    port: '3306',
    password: '', // Contraseña de MySQL
    database: 'eltiempo', // Nombre de tu base de datos
});

db.connect((err) => {
    if (err) {
        console.error('Error conectando a la base de datos:', err);
        return;
    }
    console.log('Conexión exitosa a la base de datos');
});

// Endpoints CRUD
app.get('/provincias', (req, res) => {
    const query = 'SELECT * FROM provincias';
    db.query(query, (err, results) => {
        if (err) throw err;
        res.send(results);
    });
});


app.post('/provincias', (req, res) => {
    const newItem = req.body;
    const query = 'INSERT INTO provincias SET ?';
    db.query(query, newItem, (err, results) => {
        if (err) throw err;
        res.send({ id: results.insertId, ...newItem });
    });
});

app.put('/provincias/:id', (req, res) => {
    const { id } = req.params;
    const updatedItem = req.body;
    const query = 'UPDATE provincias SET ? WHERE id_provincia = ?';
    db.query(query, [updatedItem, id], (err, results) => {
        if (err) throw err;
        res.send(results);
    });
});

app.delete('/provincias/:id', (req, res) => {
    const { id } = req.params;
    const query = 'DELETE FROM provincias WHERE id_provincia = ?';
    db.query(query, [id], (err, results) => {
        if (err) throw err;
        res.send(results);
    });
});

app.get('/provincias/:id', (req, res) => {
    const { id } = req.params;
    const query = 'SELECT * FROM provincias WHERE id_provincia = ?';
    db.query(query, [id], (err, results) => {
        if (err) throw err;
        res.send(results);
    });
});

// Iniciar el servidor
const PORT = 3000;
app.listen(PORT, () => {
    console.log(`Servidor corriendo en http://localhost:${PORT}`);
});

