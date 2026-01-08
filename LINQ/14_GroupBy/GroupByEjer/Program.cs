using System;
using System.Collections.Generic;
using System.Linq;

namespace GroupByEjer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            /*
            Ejercicio 1: Agrupar alumnos por curso y calcular estadísticas
            En un instituto, se tienen alumnos con nombre, curso y nota media.
            Agrupa a los alumnos por curso y calcula:
            número de alumnos, nota mínima, máxima, promedio y si todos aprobaron (nota ≥ 5).
            */

            var alumnos = new List<Alumno> {
                new Alumno{Nombre="Ana", Curso="Matemáticas", Nota=8.5},
                new Alumno{Nombre="Luis", Curso="Matemáticas", Nota=4.2},
                new Alumno{Nombre="Marta", Curso="Historia", Nota=6.7},
                new Alumno{Nombre="Pedro", Curso="Historia", Nota=9.1},
                new Alumno{Nombre="Lucía", Curso="Lengua", Nota=7.5},
                new Alumno{Nombre="Carlos", Curso="Lengua", Nota=5.0}
            };

            var ejercicio1 = alumnos
                .GroupBy(a => a.Curso)
                .Select(g => new
                {
                    Curso = g.Key,
                    NumeroAlumnos = g.Count(),
                    NotaMinima = g.Min(a => a.Nota),
                    NotaMaxima = g.Max(a => a.Nota),
                    NotaMedia = g.Average(a => a.Nota),
                    TodosAprobados = g.All(a => a.Nota >= 5)
                });

            foreach (var c in ejercicio1)
            {
                Console.WriteLine($"{c.Curso} | Alumnos: {c.NumeroAlumnos} | Min: {c.NotaMinima} | Max: {c.NotaMaxima} | Media: {c.NotaMedia:F2} | Todos aprobados: {c.TodosAprobados}");
            }

            /*
            Ejercicio 2: Filtrar y agrupar por año de nacimiento
            Tenemos alumnos con su fecha de nacimiento.
            Agrupa por año de nacimiento y muestra los nombres ordenados alfabéticamente.
            Además, imprime el primero y último alumno de cada año.
            */

            var alumnosFecha = new List<AlumnoFecha> {
                new AlumnoFecha{Nombre="Ana", FechaNacimiento=new DateTime(DateTime.Now.Year-20,5,12)},
                new AlumnoFecha{Nombre="Luis", FechaNacimiento=new DateTime(DateTime.Now.Year-21,3,8)},
                new AlumnoFecha{Nombre="Marta", FechaNacimiento=new DateTime(DateTime.Now.Year-20,11,2)},
                new AlumnoFecha{Nombre="Pedro", FechaNacimiento=new DateTime(DateTime.Now.Year-22,7,19)},
                new AlumnoFecha{Nombre="Lucía", FechaNacimiento=new DateTime(DateTime.Now.Year-21,1,25)}
            };

            var ejercicio2 = alumnosFecha
                .GroupBy(a => a.FechaNacimiento.Year)
                .Select(g => new
                {
                    Año = g.Key,
                    Nombres = g.Select(a => a.Nombre).OrderBy(n => n).ToList(),
                    Primero = g.OrderBy(a => a.Nombre).First().Nombre,
                    Ultimo = g.OrderBy(a => a.Nombre).Last().Nombre
                });

            foreach (var g in ejercicio2)
            {
                Console.WriteLine($"Año {g.Año}: {string.Join(", ", g.Nombres)} | Primero: {g.Primero} | Último: {g.Ultimo}");
            }

            /*
            Ejercicio 3: Agrupar por asignaturas y contar alumnos distintos
            Cada alumno puede estar en varias asignaturas.
            Agrupa por asignatura y muestra los alumnos distintos ordenados,
            cuántos hay y si la lista contiene a "Ana".
            */

            var alumnosAsignatura = new List<AlumnoAsignatura> {
                new AlumnoAsignatura{Nombre="Ana", Asignaturas=new List<string>{"Matemáticas","Historia"}},
                new AlumnoAsignatura{Nombre="Luis", Asignaturas=new List<string>{"Matemáticas","Lengua"}},
                new AlumnoAsignatura{Nombre="Marta", Asignaturas=new List<string>{"Historia","Lengua"}},
                new AlumnoAsignatura{Nombre="Pedro", Asignaturas=new List<string>{"Matemáticas"}},
                new AlumnoAsignatura{Nombre="Lucía", Asignaturas=new List<string>{"Lengua"}},
                new AlumnoAsignatura{Nombre="Carlos", Asignaturas=new List<string>{"Historia"}},
                new AlumnoAsignatura{Nombre="Sofía", Asignaturas=new List<string>{"Matemáticas","Historia","Lengua"}},
                new AlumnoAsignatura{Nombre="Javier", Asignaturas=new List<string>{"Matemáticas","Historia"}},
                new AlumnoAsignatura{Nombre="Elena", Asignaturas=new List<string>{"Lengua","Matemáticas"}},
                new AlumnoAsignatura{Nombre="Diego", Asignaturas=new List<string>{"Historia"}}
            };

            var ejercicio3 = alumnosAsignatura
                .SelectMany(a => a.Asignaturas, (a, asig) => new { a.Nombre, Asignatura = asig })
                .GroupBy(x => x.Asignatura)
                .Select(g => new
                {
                    Asignatura = g.Key,
                    Alumnos = g.Select(x => x.Nombre).Distinct().OrderBy(n => n).ToList(),
                    Total = g.Select(x => x.Nombre).Distinct().Count(),
                    ContieneAna = g.Any(x => x.Nombre == "Ana")
                });

            foreach (var a in ejercicio3)
            {
                Console.WriteLine($"{a.Asignatura} | Total: {a.Total} | Contiene Ana: {a.ContieneAna} | Alumnos: {string.Join(", ", a.Alumnos)}");
            }

            /*
            Ejercicio 4: Uso de Skip, Take y condiciones lógicas
            Se tienen notas de exámenes.
            Agrupa por curso y muestra las 2 primeras notas aprobadas (>=5)
            y las 2 últimas notas suspensas (<5).
            */

            var notas = new List<Nota> {
                new Nota{Curso="Matemáticas", Valor=8},
                new Nota{Curso="Matemáticas", Valor=3},
                new Nota{Curso="Matemáticas", Valor=6},
                new Nota{Curso="Historia", Valor=4},
                new Nota{Curso="Historia", Valor=7},
                new Nota{Curso="Historia", Valor=2},
                new Nota{Curso="Lengua", Valor=9},
                new Nota{Curso="Lengua", Valor=5},
                new Nota{Curso="Lengua", Valor=4}
            };

            var ejercicio4 = notas
                .GroupBy(n => n.Curso)
                .Select(g => new
                {
                    Curso = g.Key,
                    Aprobadas = g.Where(n => n.Valor >= 5).OrderBy(n => n.Valor).Take(2).Select(n => n.Valor),
                    Suspensas = g.Where(n => n.Valor < 5).OrderByDescending(n => n.Valor).Take(2).Select(n => n.Valor)
                });

            foreach (var c in ejercicio4)
            {
                Console.WriteLine($"{c.Curso} | Aprobadas: {string.Join(", ", c.Aprobadas)} | Suspensas: {string.Join(", ", c.Suspensas)}");
            }

            /*
            Ejercicio 5: Agrupar por asistencia y aplicar negación lógica
            Los alumnos tienen registros de asistencia (boolean).
            Agrupa por curso y muestra:
            cuántos alumnos asistieron, cuántos faltaron,
            si todos asistieron y si existe algún curso donde no todos asistieron.
            */

            var asistencias = new List<Asistencia> {
                new Asistencia{Curso="Matemáticas", Alumno="Ana", Asistio=true},
                new Asistencia{Curso="Matemáticas", Alumno="Luis", Asistio=false},
                new Asistencia{Curso="Historia", Alumno="Marta", Asistio=true},
                new Asistencia{Curso="Historia", Alumno="Pedro", Asistio=true},
                new Asistencia{Curso="Lengua", Alumno="Lucía", Asistio=false},
                new Asistencia{Curso="Lengua", Alumno="Carlos", Asistio=true}
            };

            var ejercicio5 = asistencias
                .GroupBy(a => a.Curso)
                .Select(g => new
                {
                    Curso = g.Key,
                    Asistieron = g.Count(a => a.Asistio),
                    Faltaron = g.Count(a => !a.Asistio),
                    TodosAsistieron = g.All(a => a.Asistio)
                });

            bool existeCursoConFaltas = ejercicio5.Any(c => !c.TodosAsistieron);

            foreach (var c in ejercicio5)
            {
                Console.WriteLine($"{c.Curso} | Asistieron: {c.Asistieron} | Faltaron: {c.Faltaron} | Todos asistieron: {c.TodosAsistieron}");
            }

            Console.WriteLine($"¿Existe algún curso donde no todos asistieron? {existeCursoConFaltas}");

            /*
            Ejercicio 6: Calcular el número de apariciones de cada carácter dentro de una palabra.
            Mostrar el resultado ordenado por carácter.
            */

            string[] palabras = { "casa", "perro", "sol", "coche" };

            var ejercicio6 = palabras
                .SelectMany(p => p)
                .GroupBy(c => c)
                .OrderBy(g => g.Key)
                .Select(g => new { Caracter = g.Key, Total = g.Count() });

            foreach (var c in ejercicio6)
            {
                Console.WriteLine($"{c.Caracter}: {c.Total}");
            }
        }
    }

    class Alumno
    {
        public string Nombre { get; set; }
        public string Curso { get; set; }
        public double Nota { get; set; }
    }

    class AlumnoFecha
    {
        public string Nombre { get; set; }
        public DateTime FechaNacimiento { get; set; }
    }

    class AlumnoAsignatura
    {
        public string Nombre { get; set; }
        public List<string> Asignaturas { get; set; }
    }

    class Nota
    {
        public string Curso { get; set; }
        public double Valor { get; set; }
    }

    class Asistencia
    {
        public string Curso { get; set; }
        public string Alumno { get; set; }
        public bool Asistio { get; set; }
    }
}
