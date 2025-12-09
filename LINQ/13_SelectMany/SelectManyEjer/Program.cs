using static System.Runtime.InteropServices.JavaScript.JSType;

namespace SelectManyEjer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            /*
             Ejercicio 1:
             Cada alumno tiene varias notas en diferentes asignaturas.
            Queremos calcular la nota media de todos los alumnos (mostrarla con dos decimales) , la nota mínima y máxima, y verificar si algún alumno tiene una nota menor que 5.
            Listar los alumnos con alguna nota suspendida
             */
            var alumnos = new List<AlumnoNotas>
{
    new AlumnoNotas { Nombre = "Pedro", Notas = new List<int> { 7, 8, 9 } },
    new AlumnoNotas { Nombre = "Lucía", Notas = new List<int> { 4, 6, 5 } },
    new AlumnoNotas { Nombre = "Carlos", Notas = new List<int> { 10, 9, 8 } }
};
            var notaMedia = alumnos.SelectMany(a => a.Notas).Average();
            var notaMinima = alumnos.SelectMany(a => a.Notas).Min();
            var notaMaxima = alumnos.SelectMany(a => a.Notas).Max();
            var algunAlumnoSuspenso = alumnos.SelectMany(a => a.Notas).Any(n => n < 5);
            var alumnosConSuspensos = alumnos.Where(a => a.Notas.Any(n => n < 5)).Select(a => a.Nombre).ToList();
            Console.WriteLine($"Nota media: {notaMedia:F2}");
            Console.WriteLine($"Nota mínima: {notaMinima}");
            Console.WriteLine($"Nota máxima: {notaMaxima}");
            Console.WriteLine($"Alumnos supensos {(algunAlumnoSuspenso)}");
            Console.WriteLine("Alumnos con alguna nota suspendida:");
            for (var i = 0; i < alumnosConSuspensos.Count; i++)
            {
                Console.WriteLine(alumnosConSuspensos[i]);
            }
            /*
             * Ejercicio 2:
             * Cada alumno entrega varios trabajos en distintas fechas.Queremos obtener todas las fechas de entrega, ordenarlas, mostrar la primera y la última, y comprobar si todos los alumnos entregaron al menos un trabajo este año.
             */
            var alumnos2 = new List<AlumnoTrabajos>
{
    new AlumnoTrabajos { Nombre = "Sofía", FechasEntrega = new List<DateTime> { new DateTime(DateTime.Now.Year, 3, 10), new DateTime(DateTime.Now.Year, 5, 20) } },
    new AlumnoTrabajos { Nombre = "Diego", FechasEntrega = new List<DateTime> { new DateTime(DateTime.Now.Year, 2, 15) } },
    new AlumnoTrabajos { Nombre = "Laura", FechasEntrega = new List<DateTime> { new DateTime(DateTime.Now.Year, 6, 5), new DateTime(DateTime.Now.Year, 7, 1) } }
};
            var fechasEntrega = alumnos2.SelectMany(a => a.FechasEntrega).OrderBy(d => d).ToList();
            var primeraFecha = fechasEntrega.First();
            var ultimaFecha = fechasEntrega.Last();
            var todosEntregaronEsteAno = alumnos2.All(a => a.FechasEntrega.Any(d => d.Year == DateTime.Now.Year));
            for (var i = 0; i < fechasEntrega.Count; i++)
            {
                Console.WriteLine($"{fechasEntrega[i]:dd/MM/yyyy}");
            }
            Console.WriteLine($"Primera fecha de entrega: {primeraFecha:dd/MM/yyyy}");
            Console.WriteLine($"Última fecha de entrega: {ultimaFecha:dd/MM/yyyy}");
            Console.WriteLine($"Todos entregaron este año: {todosEntregaronEsteAno}");
            /* 
             * Ejercicio 3: Cursos y alumnos
             * En un instituto, cada alumno está inscrito en varios cursos. 
             * Queremos obtener una lista de todos los cursos distintos en los que hay alumnos inscritos este año, ordenados alfabéticamente, y contar cuántos cursos hay en total.
             */

            var alumnos3 = new List<Alumno>
        {
            new Alumno { Nombre = "Ana", Cursos = new List<string> { "Matemáticas", "Historia", "Física" } },
            new Alumno { Nombre = "Luis", Cursos = new List<string> { "Biología", "Química", "Matemáticas" } },
            new Alumno { Nombre = "Marta", Cursos = new List<string> { "Historia", "Lengua" } }
        };
            var cursos = alumnos3
    .SelectMany(a => a.Cursos)
    .Distinct()
    .OrderBy(c => c)
    .ToList();

            Console.WriteLine("Cursos:");
            for (var i = 0; i < cursos.Count; i++)
            {
                Console.WriteLine(cursos[i]);
            }
            Console.WriteLine($"Total cursos: {cursos.Count}");

            /*
             * Ejercicio 4: Alumnos y edades
En una lista de alumnos con sus calificaciones y fecha de nacimiento, encuentra al único alumno que cumple con todas estas condiciones:
Tiene una media superior a 9.
Ha aprobado todas las asignaturas.
Nació antes del año 2005.
Su nombre no contiene la letra "z" o “Z”.

Además, imprime:
Si hay algún alumno con más de 3 asignaturas suspendidas.
El número total de alumnos.
La nota media general con dos decimales,.
La nota más alta y más baja.
El nombre del alumno con la nota más alta.

             */
            var alumnos4 = new List<Alumno4> {
            new Alumno4 { Nombre = "Lucia", FechaNacimiento = new DateTime(2004, 3, 15), Notas = new List<double> { 9.5, 9.8, 10 } },
            new Alumno4 { Nombre = "Carlos", FechaNacimiento = new DateTime(2006, 5, 20), Notas = new List<double> { 7.5, 8.0, 6.5 } },
            new Alumno4 { Nombre = "Marta", FechaNacimiento = new DateTime(2003, 11, 2), Notas = new List<double> { 9.0, 9.2, 9.1 } },
            new Alumno4 { Nombre = "Alonzo", FechaNacimiento = new DateTime(2002, 1, 10), Notas = new List<double> { 10, 10, 10 } }
        };
            var alumnoCumpleCondiciones = alumnos4.SingleOrDefault(a =>
                a.Notas.Average() > 9 &&
                a.Notas.All(n => n >= 5) &&
                a.FechaNacimiento.Year < 2005 &&
                !a.Nombre.ToLower().Contains("z")
            );
            var algunAlumnoMasDe3Suspensos = alumnos4.Any(a => a.Notas.Count(n => n < 5) > 3);
            var numeroTotalAlumnos = alumnos4.Count;
            var notaMediaGeneral = alumnos4.SelectMany(a => a.Notas).Average();
            var notaMasAlta = alumnos4.SelectMany(a => a.Notas).Max();
            var notaMasBaja = alumnos4.SelectMany(a => a.Notas).Min();
            var alumnoConNotaMasAlta = alumnos4
                .First(a => a.Notas.Contains(notaMasAlta))
                .Nombre;
            Console.WriteLine($"Alumno que cumple condiciones: {alumnoCumpleCondiciones?.Nombre ?? "Ninguno"}");
            Console.WriteLine($"Algún alumno con más de 3 suspensos: {algunAlumnoMasDe3Suspensos}");
            Console.WriteLine($"Número total de alumnos: {numeroTotalAlumnos}");
            Console.WriteLine($"Nota media general: {notaMediaGeneral:F2}");
            Console.WriteLine($"Nota más alta: {notaMasAlta}");
            Console.WriteLine($"Nota más baja: {notaMasBaja}");
            Console.WriteLine($"Alumno con la nota más alta: {alumnoConNotaMasAlta}");

            /*
             Ejercicio 5 : Control de asistencia y rendimiento
En una lista de alumnos con su asistencia diaria (booleanos) y notas, encuentra al único alumno que:
Ha asistido todos los días.
Tiene al menos una nota superior a 9.
No tiene ninguna nota inferior a 6.
Su nombre empieza por "A" o termina en "o".

Además, imprime:
Cuántos alumnos no han asistido ningún día.
La suma total de todas las notas.
La media de notas por alumno .

             */
            var alumnos5 = new List<Alumno5> {
         new Alumno5 { Nombre = "Alberto", Asistencia = new List<bool> { true, true, true }, Notas = new List<double> { 9.5, 8.0, 7.5 } },
            new Alumno5 { Nombre = "Mario", Asistencia = new List<bool> { true, true, true }, Notas = new List<double> { 10, 9.5, 9.8 } },
            new Alumno5 { Nombre = "Lucia", Asistencia = new List<bool> { false, false, false }, Notas = new List<double> { 6.0, 7.0, 8.0 } }
        };
            var alumnoAsistenciaRendimiento = alumnos5.SingleOrDefault(a =>
                a.Asistencia.All(d => d) &&
                a.Notas.Any(n => n > 9) &&
                a.Notas.All(n => n >= 6) &&
                (a.Nombre.StartsWith("A") || a.Nombre.EndsWith("o"))
            );
            var alumnosSinAsistencia = alumnos5.Count(a => a.Asistencia.All(d => !d));
            var sumaTotalNotas = alumnos5.SelectMany(a => a.Notas).Sum();
            var mediaNotasPorAlumno = alumnos5.Select(a => a.Notas.Average()).ToList();
            Console.WriteLine($"Alumno que cumple condiciones: {alumnoAsistenciaRendimiento?.Nombre ?? "Ninguno"}");
            Console.WriteLine($"Alumnos sin asistencia: {alumnosSinAsistencia}");
            Console.WriteLine($"Suma total de todas las notas: {sumaTotalNotas}");
            Console.WriteLine("Media de notas por alumno:");
            for (var i = 0; i < mediaNotasPorAlumno.Count; i++)
            {
                Console.WriteLine($"{mediaNotasPorAlumno[i]:F2}");
            }
            /*
             Ejercicio 6 : Evaluación de proyectos finales

En una lista de alumnos con su proyecto final (nota, fecha de entrega, aprobado), encuentra al único alumno que:
Entregó el proyecto antes del 1 de junio de 2025.
Obtuvo una nota superior a 8.
El proyecto está marcado como aprobado.
Su nombre no contiene vocales repetidas.

Además, imprime:
Cuántos alumnos entregaron tarde. ( > 1 de junio de 2025) 
Cuántos no aprobaron.
La nota media de los proyectos aprobados.
El nombre del alumno con el proyecto más reciente.

             */
            var proyectos = new List<ProyectoFinal> {
            new ProyectoFinal { NombreAlumno = "Elena", Nota = 9.0, FechaEntrega = new DateTime(2025, 5, 20), Aprobado = true },
            new ProyectoFinal { NombreAlumno = "Luis", Nota = 7.5, FechaEntrega = new DateTime(2025, 6, 10), Aprobado = false },
            new ProyectoFinal { NombreAlumno = "Mauro", Nota = 8.5, FechaEntrega = new DateTime(2025, 4, 30), Aprobado = true }
        };

            var elegido = proyectos.Single(p =>
                p.FechaEntrega < new DateTime(2025, 6, 1) &&
                p.Nota > 8 &&
                p.Aprobado &&
                      p.NombreAlumno.ToLower().Where(c => "aeiou".Contains(c))
                                 .GroupBy(c => c)
                                 .All(g => g.Count() == 1)
            );
            var aprobados = proyectos.Where(p => p.Aprobado);
            var masReciente = proyectos.OrderByDescending(p => p.FechaEntrega).First();

            Console.WriteLine($"Proyecto elegido: {elegido.NombreAlumno}");
            Console.WriteLine($"Entregas tarde: {proyectos.Count(p => p.FechaEntrega >= new DateTime(2025, 6, 1))}");
            Console.WriteLine($"No aprobados: {proyectos.Count(p => !p.Aprobado)}");
            Console.WriteLine($"Media notas aprobados: {aprobados.Average(p => p.Nota):F2}");
            Console.WriteLine($"Proyecto más reciente: {masReciente.NombreAlumno}");
            /*
             Ejercicio 7 : Reconocimiento al esfuerzo académico
En una lista de alumnos con sus notas trimestrales, esfuerzo (booleano) y fecha de inscripción, encuentra al único alumno que merece el reconocimiento al esfuerzo académico, cumpliendo estas condiciones:
Ha mostrado esfuerzo en todos los trimestres.
Tiene una media de notas entre 6 y 8.
Se inscribió antes del 1 de septiembre de 2024.
Su nombre no contiene ninguna letra del nombre "Luis".(no diferenciamos mayusculas de minusculas)

Además, imprime:
Cuántos alumnos tienen notas por debajo de 5.
La nota más baja y más alta del grupo.
La suma total de todas las notas.
El nombre del alumno con la media más cercana a 7 ya sea por arriba o por abajo.
             */

            var alumnos7 = new List<Alumno7> {
            new Alumno7 { Nombre = "Marta", FechaInscripcion = new DateTime(2024, 6, 15), Notas = new List<double> { 6.5, 7.0, 7.5 }, Esfuerzo = new List<bool> { true, true, true } },
            new Alumno7 { Nombre = "Luis", FechaInscripcion = new DateTime(2024, 9, 10), Notas = new List<double> { 8.5, 9.0, 9.5 }, Esfuerzo = new List<bool> { true, false, true } },
            new Alumno7 { Nombre = "Clara", FechaInscripcion = new DateTime(2024, 5, 20), Notas = new List<double> { 6.0, 6.5, 7.0 }, Esfuerzo = new List<bool> { true, true, true } }
        };

            var alumnoReconocimiento = alumnos7.Single(a =>
                a.Esfuerzo.All(e => e) &&
                a.Notas.Average() >= 6 && a.Notas.Average() <= 8 &&
                a.FechaInscripcion < new DateTime(2024, 9, 1) &&
                !a.Nombre.ToLower().Any(c => "luis".Contains(c))
            );
            var alumnosConNotasBajo5 = alumnos.Count(a => a.Notas.Any(n => n < 5));
            var notaMasBaja7 = alumnos.SelectMany(a => a.Notas).Min();
            var notaMasAlta7 = alumnos.SelectMany(a => a.Notas).Max();
            var sumaTotalNotas7 = alumnos.SelectMany(a => a.Notas).Sum();
            var alumnoCercanoA7 = alumnos
                .OrderBy(a => Math.Abs(a.Notas.Average() - 7))
                .First()
                .Nombre;
            Console.WriteLine($"Alumno con reconocimiento: {alumnoReconocimiento.Nombre}");
            Console.WriteLine($"Alumnos con notas por debajo de 5: {alumnosConNotasBajo5}");
            Console.WriteLine($"Nota más baja: {notaMasBaja7}");
            Console.WriteLine($"Nota más alta: {notaMasAlta7}");
            Console.WriteLine($"Suma total de todas las notas: {sumaTotalNotas7}");
            Console.WriteLine($"Alumno con media más cercana a 7: {alumnoCercanoA7}");

            /*
             * Ejercicio 8: Evaluación integral de alumnos. 2 niveles
             Cada alumno tiene varias asignaturas, y cada asignatura tiene varias notas. Queremos:
Calcular el promedio de cada alumno considerando todas sus asignaturas y notas.
Calcular los promedios por asignatura y además el promedio global de cada alumno 
Mostrar el alumno con la mayor nota promedio.
Filtrar los alumnos que tienen todas las notas mayores o iguales a 6 y al menos una nota de 10.
             */
            var alumnos8 = new List<AlumnoAsignaturas>
    {
        new AlumnoAsignaturas { Nombre = "Ana", Asignaturas = new List<List<int>> { new List<int>{8,9,10}, new List<int>{7,6,8} } },
        new AlumnoAsignaturas { Nombre = "Luis", Asignaturas = new List<List<int>> { new List<int>{5,6,7}, new List<int>{6,6,6} } },
        new AlumnoAsignaturas { Nombre = "Marta", Asignaturas = new List<List<int>> { new List<int>{10,10,9}, new List<int>{8,9,10} } }
    };
            var promediosAlumnos = alumnos8.Select(a => new
            {
                a.Nombre,
                Promedio = a.Asignaturas.SelectMany(asig => asig).Average()
            }).ToList();
            foreach (var alumno in promediosAlumnos)
            {
                Console.WriteLine($"Alumno: {alumno.Nombre}, Promedio: {alumno.Promedio:F2}");
            }
            Console.WriteLine(
                $"Alumno con mayor nota promedio: " +
                $"{promediosAlumnos.OrderByDescending(a => a.Promedio).First().Nombre}"
            );
            var alumnosFiltrados = alumnos8.Where(a =>
                a.Asignaturas.SelectMany(asig => asig).All(n => n >= 6) &&
                a.Asignaturas.SelectMany(asig => asig).Any(n => n == 10)
            ).ToList();
            Console.WriteLine("Alumnos que cumplen el filtro:");
            foreach (var alumno in alumnosFiltrados)
            {
                Console.WriteLine(alumno.Nombre);
            }
    

        }
        public class AlumnoNotas
        {
            public string Nombre { get; set; }
            public List<int> Notas { get; set; }
        }
        public class AlumnoTrabajos
        {
            public string Nombre { get; set; }
            public List<DateTime> FechasEntrega { get; set; }
        }

        class Alumno
        {
            public string Nombre { get; set; }
            public List<string> Cursos { get; set; }
        }

        class Alumno4
        {
            public string Nombre { get; set; }
            public DateTime FechaNacimiento { get; set; }
            public List<double> Notas { get; set; }
        }

        class Alumno5
        {
            public string Nombre { get; set; }
            public List<bool> Asistencia { get; set; }
            public List<double> Notas { get; set; }
        }

        class ProyectoFinal
        {
            public string NombreAlumno { get; set; }
            public double Nota { get; set; }
            public DateTime FechaEntrega { get; set; }
            public bool Aprobado { get; set; }
        }

        class Alumno7
        {
            public string Nombre { get; set; }
            public DateTime FechaInscripcion { get; set; }
            public List<double> Notas { get; set; }
            public List<bool> Esfuerzo { get; set; }
        }
        public class AlumnoAsignaturas
        {
            // Nombre del alumno
            public string Nombre { get; set; }

            // Cada asignatura tiene una lista de notas (int)
            public List<List<int>> Asignaturas { get; set; }
        }

    }
}
