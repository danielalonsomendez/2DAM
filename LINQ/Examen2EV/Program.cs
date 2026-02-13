using static System.Runtime.InteropServices.JavaScript.JSType;

namespace Linq2Eval
{
    internal class Program
    {
        static void Main(string[] args)
        {
            // Ejercicio 1:
            // En una lista de alumnos con fechas de entrega de tareas, encuentra el primer alumno
            // que ha entregado tareas solo en días pares del mes. → Mostrar el nombre del alumno

            var alumnos28 = new List<(string Nombre, List<DateTime> Entregas)>
            {
                ("Mario", new List<DateTime> { new DateTime(2025, 10, 2), new DateTime(2025, 10, 4), new DateTime(2025, 10, 6) }),
                ("Clara", new List<DateTime> { new DateTime(2025, 10, 1), new DateTime(2025, 10, 3) }),
                ("Jorge", new List<DateTime> { new DateTime(2025, 10, 8), new DateTime(2025, 10, 10) }),
                ("Sara", new List<DateTime> { new DateTime(2025, 10, 5), new DateTime(2025, 10, 7) })
            };
            var primerAlumnoPares = alumnos28
                .FirstOrDefault(a => a.Entregas.All(e => e.Day % 2 == 0))
                .Nombre;

            Console.WriteLine("Ejer1: " + primerAlumnoPares);

            // Ejercicio 2:
            // Obtener el segundo departamento  con mayor número de empleados mayores de 40 años
            List<Departamento> departamentos = new List<Departamento> {
                new Departamento{ Nombre = "Ventas", Empleados = new List<Empleado>{ new Empleado{Edad=45}, new Empleado{Edad=38} } },
                new Departamento{ Nombre = "Marketing", Empleados = new List<Empleado>{ new Empleado{Edad=42}, new Empleado{Edad=41}, new Empleado{Edad=39} } },
                new Departamento{ Nombre = "IT", Empleados = new List<Empleado>{ new Empleado{Edad=50}, new Empleado{Edad=47}, new Empleado{Edad=43} } }
            };

            var segundoDepartamentoMay40 = departamentos
                .Select(d => new
                {
                    Nombre = d.Nombre,
                    Mayores40 = d.Empleados.Count(e => e.Edad > 40)
                })
                .OrderByDescending(d => d.Mayores40)
                .Skip(1)
                .FirstOrDefault();


            Console.WriteLine("Ejer2: " + segundoDepartamentoMay40.Nombre + " - "+ segundoDepartamentoMay40.Mayores40);

            // Ejercicio 3:
            // Obtener el nombre del empleado y la suma de horas trabajadas

            List<Empleado20> empleados20 = new List<Empleado20> {
                new Empleado20{ Nombre = "Ana", Horas = new List<int>{ 8, 8, 8 } },
                new Empleado20{ Nombre = "Luis", Horas = new List<int>{ 9, 9, 9, 9 } },
                new Empleado20{ Nombre = "Bea", Horas = new List<int>{ 10, 10 } }
            };

            var horastrabajadas = empleados20
                  .Select(e => new { e.Nombre, TotalHoras = e.Horas.Sum() });

            Console.WriteLine("Ejer3:");
            foreach (var empleado in horastrabajadas) { 
                Console.WriteLine(empleado.Nombre+" - "+empleado.TotalHoras);
            }


            // Ejercicio4  
            // Cada alumno tiene varias notas en diferentes asignaturas.
            // Queremos calcular la nota media de todos los alumnos(mostrarla con dos decimales),
            // Además, listar los nombres de los alumnos con sus notas suspendidas.

            var alumnos = new List<AlumnoNotas>
            {
                new AlumnoNotas { Nombre = "Pedro", Notas = new List<int> { 7, 8, 9 } },
                new AlumnoNotas { Nombre = "Ana", Notas = new List<int> { 4, 6, 5 } },
                new AlumnoNotas { Nombre = "Carlos", Notas = new List<int> { 4, 3, 8 } }
            };

            var mediaGeneral = alumnos
                .SelectMany(a => a.Notas)
                .Average();

            var suspendidos = alumnos
                .Where(a => a.Notas.Any(n => n < 5))
                .Select(a => a.Nombre);

            Console.WriteLine("Ejer4:");
            Console.WriteLine("Media: "+ mediaGeneral.ToString() );
            foreach (var suspendido in suspendidos)
            {
                Console.WriteLine(suspendido);
            }




            // Ejercicio5
            // En un instituto, cada alumno está inscrito en varios cursos.
            // Queremos obtener una lista de todos los cursos distintos en los que hay alumnos inscritos
            // este año, ordenados alfabéticamente.


            var alumnos03 = new List<Alumno>
            {
                new Alumno { Nombre = "Ana", Cursos = new List<string> { "Matemáticas", "Historia", "Física" } },
                new Alumno { Nombre = "Luis", Cursos = new List<string> { "Biología", "Química", "Matemáticas" } },
                new Alumno { Nombre = "Marta", Cursos = new List<string> { "Historia", "Lengua" } }
            };

            var cursos = alumnos03
                .SelectMany(a => a.Cursos)
                .Distinct()
                .OrderBy(c => c);

            Console.WriteLine("Ejer5:");
            foreach (var c in cursos)
            {
                Console.WriteLine(c);
            }

            // Ejercicio 6:
            // Se tiene una lista de alumnos con sus calificaciones en varios exámenes. 
            // Filtra aquellos alumnos que tienen al menos una nota superior a 8, 
            // que no hayan suspendido ningún examen y que hayan asistido a clase. 
            // Ordena los resultados por promedio de notas descendente. 
            // Muestra el nombre, promedio, nota máxima y mínima de cada alumno filtrado.

            var alumnos11 = new List<Alumno11>
            {
                new Alumno11 { Nombre = "Lucía", Notas = new List<double> { 9.5, 8.2, 7.8 }, Asistencia = true },
                new Alumno11 { Nombre = "Carlos", Notas = new List<double> { 6.0, 4.5, 7.0 }, Asistencia = true },
                new Alumno11 { Nombre = "Marta", Notas = new List<double> { 8.0, 8.5, 9.0 }, Asistencia = false },
                new Alumno11 { Nombre = "Javier", Notas = new List<double> { 9.0, 9.5, 10.0 }, Asistencia = true },
                new Alumno11 { Nombre = "Ana", Notas = new List<double> { 5.0, 6.0, 7.0 }, Asistencia = true }
            };
            var resultado6 = alumnos11
                .Where(a => a.Asistencia && a.Notas.Any(n => n > 8) && a.Notas.All(n => n >= 5))
                .Select(a => new{
                    a.Nombre,
                    Promedio = a.Notas.Average(),
                    Max = a.Notas.Max(),
                    Min = a.Notas.Min()
                })
                .OrderByDescending(a => a.Promedio);

            Console.WriteLine("Ejer6:");
            foreach (var a in resultado6)
            {
                Console.WriteLine(a.Nombre +"-"+a.Promedio+"-"+a.Max+"-"+a.Min);
            }

            // EJERCICIO 7
            // Mostrar nombre del alumno y su promedio de notas.
            // Mostrar también si todos los alumnos aprobaron o no.


            var alumnos2 = new[]
            {
                new { Id = 1, Nombre = "Ana" },
                new { Id = 2, Nombre = "Luis" },
                new { Id = 3, Nombre = "María" }
            };

            var notas2 = new[]
            {
                new { AlumnoId = 1, Materia = "Matemáticas", Valor = 8 },
                new { AlumnoId = 1, Materia = "Lengua", Valor = 7 },
                new { AlumnoId = 2, Materia = "Matemáticas", Valor = 4 },
                new { AlumnoId = 3, Materia = "Historia", Valor = 9 },
            };

            var promedios = alumnos2
                .Join(notas2,
                alumno => alumno.Id,
                notas => notas.AlumnoId,
                (a, n) => new { a.Nombre, n.Valor })
                .GroupBy(a => a.Nombre)
                .Select(a => new { Nombre = a.Key, Promedio = a.Average(x => x.Valor) });
            bool todosAprueban = notas2.All(n => n.Valor >= 5);

            Console.WriteLine("Ejer7:");
            foreach (var a in promedios)
            {
                Console.WriteLine(a.Nombre+" - "+a.Promedio);
            }
            Console.WriteLine("Todos aprobaron: " + todosAprueban );


            // Ejercicio 8: 
            // Agrupa a los alumnos por curso y calcula por cada curso:
            // Número de alumnos, nota mínima, máxima, promedio, y si todos aprobaron o no

            var alumnosagru = new List<Alumno2> {
                new Alumno2{Nombre="Ana", Curso="Matemáticas", Nota=8.5},
                new Alumno2{Nombre="Luis", Curso="Matemáticas", Nota=4.2},
                new Alumno2{Nombre="Marta", Curso="Historia", Nota=6.7},
                new Alumno2{Nombre="Pedro", Curso="Historia", Nota=9.1},
                new Alumno2{Nombre="Lucía", Curso="Lengua", Nota=7.5},
                new Alumno2{Nombre="Carlos", Curso="Lengua", Nota=5.0}
            };
            var alumnnosagrupados = alumnosagru
                .GroupBy(a => a.Curso)
                .Select(a => new
                {
                    Curso = a.Key,
                    NumAlumnos = a.Count(),
                    Min =a.Min(a2 => a2.Nota),
                    Max = a.Max(a2 => a2.Nota),
                    Promedio = a.Average(a2 => a2.Nota),
                    TodosAprueban = a.All(a2 => a2.Nota >= 5)
                });
            Console.WriteLine("Ejer8:");
            foreach (var c in alumnnosagrupados)
            {
                Console.WriteLine(c.Curso +" - "+c.NumAlumnos+" - "+c.Min+" - "+c.Max+" - "+c.Promedio+" - "+c.TodosAprueban);
            }


            // Ejercicio 9: 
            // Agrupa por curso y muestra las 2 primeras notas aprobadas y las 2 últimas notas suspensas.

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
   

            var ejercicio9agruparprimerasultimas = notas
                .GroupBy(n => n.Curso)
                .Select(g => new
                {
                    Curso = g.Key,
                    Aprobadas = g.Where(n => n.Valor >= 5).OrderBy(n => n.Valor).Take(2),
                    Suspensas = g.Where(n => n.Valor < 5).OrderByDescending(n => n.Valor).Take(2)
                });

            Console.WriteLine("Ejer9:");
            foreach (var c in ejercicio9agruparprimerasultimas)
            {
                Console.WriteLine(c.Curso);
                Console.WriteLine("Aprobadas:");
                foreach (var aprobada in c.Aprobadas)
                {
                    Console.WriteLine(aprobada.Valor);
                }
                Console.WriteLine("Suspensas:");
                foreach (var suspendidas in c.Suspensas)
                {
                    Console.WriteLine(suspendidas.Valor);
                   
                }
            }

            // Ejercicio 10:
            // Mostrar los alumnos que hayan entregado al menos una tarea en los últimos 7 días.
            var hoy = DateTime.Today;
            var alumnos3 = new[] {
                new { Nombre = "Pedro", Entregas = new[] { hoy.AddDays(-2), hoy.AddDays(-10) } },
                new { Nombre = "Sara", Entregas = new[] { hoy.AddDays(-1), hoy.AddDays(-3) } },
                new { Nombre = "Tomás", Entregas = new[] { hoy.AddDays(-8) } }
            };
            var alumnosentregas7dias = alumnos3.Where(a => a.Entregas.Any(a => (hoy - a).TotalDays <= 7)).ToList();
            Console.WriteLine("Ejer10:");
            foreach ( var alumno in alumnosentregas7dias)
            {
                Console.WriteLine(alumno.Nombre);
            }



        }

        class Empleado { public int Edad; }
        class Empleado20 { public string Nombre; public List<int> Horas; }
        class Departamento { public string Nombre; public List<Empleado> Empleados; }
        public class AlumnoNotas
        {
            public string Nombre { get; set; }
            public List<int> Notas { get; set; }
        }
        class Alumno
        {
            public string Nombre { get; set; }
            public List<string> Cursos { get; set; }
        }

        class Alumno11
        {
            public string? Nombre { get; set; }
            public List<double> Notas { get; set; }
            public bool Asistencia { get; set; }
        }
        class Alumno2
        {
            public string Nombre { get; set; }
            public string Curso { get; set; }
            public double Nota { get; set; }
        }
        class Nota
        {
            public string Curso { get; set; }
            public double Valor { get; set; }
        }
    }
}
