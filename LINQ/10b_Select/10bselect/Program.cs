namespace _10bselect
{
    internal class Program
    {
        static void Main(string[] args)
        {
            /*
             Ejercicio 1: Estadísticas de productos en inventario
Dado un array de productos con nombre, cantidad en stock, precio unitario y si están en promoción, 
calcula el total (SUM) de valor (STOCK *PRECIO) en stock de los productos en PROMOCIÓN que tengan más de >10 unidades. Si no hay ninguno, devuelve 0. 
             */
            var productos = new[]
            {
    new { Nombre = "Teclado", Stock = 15, Precio = 25.5, Promocion = true },
    new { Nombre = "Ratón", Stock = 8, Precio = 18.0, Promocion = true },
    new { Nombre = "Monitor", Stock = 5, Precio = 150.0, Promocion = false },
    new { Nombre = "Auriculares", Stock = 20, Precio = 45.0, Promocion = true }
};
            var totalpromocion = productos.Where(p => p.Promocion && p.Stock > 10).Select(p => p.Stock * p.Precio).DefaultIfEmpty(0).Sum();
            Console.WriteLine(totalpromocion);
            /*
             Ejercicio 2: Validación de citas médicas
Dada una lista de citas médicas con fecha, nombre del paciente y si asistió, verifica si todos los pacientes que tenían cita en el mes actual asistieron. Si no hay citas este mes, devuelve false.

             */

            var citas = new List<(DateTime Fecha, string Paciente, bool Asistio)>
{
    (new DateTime(2025, 10, 1), "Juan", true),
    (new DateTime(2025, 10, 3), "Lucía", false),
    (new DateTime(2025, 9, 28), "Pedro", true),
    (new DateTime(2025, 10, 5), "María", true)
};

            var citasMesActual = citas.Where(c => c.Fecha.Month == DateTime.Now.Month && c.Fecha.Year == DateTime.Now.Year);
            bool todasAsistieron = citasMesActual.Select(c => c.Asistio).DefaultIfEmpty(true).All(a => a);
            Console.WriteLine(todasAsistieron);

            /* Ejercicio 3: Filtrado de usuarios por actividad y edad
 Dada una lista de usuarios con nombre, edad, si están activos y si han iniciado sesión en la última semana, obtén los nombres ordenados alfabéticamente de los usuarios activos mayores de 30 años que hayan iniciado sesión recientemente. Si no hay ninguno, devuelve una lista con "0".
            */

            var usuarios = new List<(string Nombre, int Edad, bool Activo, bool SesionReciente)>
{
    ("Laura", 35, true, true),
    ("Tomás", 28, true, false),
    ("Elena", 40, false, true),
    ("David", 32, true, true),
    ("Sara", 29, true, true)
};
            var usuariosFiltrados = usuarios
                .Where(u => u.Activo && u.Edad > 30 && u.SesionReciente)
                .Select(u => u.Nombre)
                .OrderBy(n => n)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", usuariosFiltrados));


            /*
             Ejercicio 4: Evaluación de rendimiento académico
             */
            var estudiantes = new[]
            {
                new { Nombre = "Alberto", Notas = new[] { 8.5, 9.2, 7.8 }, Matriculado = true },
                new { Nombre = "Bea", Notas = new[] { 6.0, 7.0, 8.0 }, Matriculado = true },
                new { Nombre = "Carlos", Notas = new[] { 9.5, 9.8, 10.0 }, Matriculado = false },
                new { Nombre = "Diana", Notas = new[] { 9.1, 8.9, 9.0 }, Matriculado = true }
            };
            var promedioFinal = estudiantes
                .Where(e => e.Matriculado && e.Notas.Any(n => n > 9))
                .Select(e => e.Notas.Average())
                .DefaultIfEmpty(0)
                .Average();
            Console.WriteLine(promedioFinal);

            /*
             Ejercicio 5: Análisis de empleados por fecha de ingreso y salario
             */
            var empleados = new List<(string Nombre, DateTime FechaIngreso, double Salario, bool Activo)>
            {
                ("Ana", new DateTime(2018, 5, 1), 3200, true),
                ("Luis", new DateTime(2021, 3, 15), 2800, true),
                ("Marta", new DateTime(2019, 7, 10), 4000, false),
                ("Carlos", new DateTime(2017, 1, 20), 3500, true),
                ("Sofía", new DateTime(2022, 11, 5), 3000, true)
            };
            var media = empleados.Average(e => e.Salario);
            var resultado = empleados
                .Where(e => e.Activo && e.FechaIngreso.Year < 2020 && e.Salario > media)
                .Select(e => new { e.Nombre, Años = DateTime.Now.Year - e.FechaIngreso.Year })
                .DefaultIfEmpty(new { Nombre = "0", Años = 0 })
                .ToList();
            resultado.ForEach(r => Console.WriteLine($"{r.Nombre} - {r.Años} años"));

            /*
             Ejercicio 6: Filtrado de facturas por fecha y estado de pago
             */
            var facturas = new List<(string Numero, DateTime Fecha, double Importe, bool Pagada)>
            {
                ("F001", DateTime.Now.AddDays(-10), 500, false),
                ("F002", DateTime.Now.AddDays(-70), 300, false),
                ("F003", DateTime.Now.AddDays(-30), 700, true),
                ("F004", DateTime.Now.AddDays(-20), 800, false),
                ("F005", DateTime.Now.AddDays(-5), 200, false)
            };
            var promedio = facturas.Average(f => f.Importe);
            var recientes = facturas
                .Where(f => !f.Pagada && (DateTime.Now - f.Fecha).TotalDays <= 60 && f.Importe > promedio)
                .Select(f => f.Numero)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", recientes));

            /*
             Ejercicio 7: Evaluación de proyectos por duración y éxito
             */
            var proyectos = new[]
            {
                new { Nombre = "Alpha", Dias = 45, Exitoso = true, Completado = true },
                new { Nombre = "Beta", Dias = 20, Exitoso = true, Completado = true },
                new { Nombre = "Gamma", Dias = 60, Exitoso = false, Completado = true },
                new { Nombre = "Delta", Dias = 35, Exitoso = true, Completado = false },
                new { Nombre = "Epsilon", Dias = 50, Exitoso = true, Completado = true }
            };
            var totalDias = proyectos
                .Where(p => p.Exitoso && p.Completado && p.Dias > 30)
                .Select(p => p.Dias)
                .DefaultIfEmpty(0)
                .Sum();
            Console.WriteLine(totalDias);

            /*
             Ejercicio 8: Validación de alumnos por asistencia y rendimiento
             */
            var alumnos8 = new List<(string Nombre, List<bool> Asistencias, List<double> Notas)>
            {
                ("Lucía", new List<bool> { true, true, true }, new List<double> { 8.5, 9.1 }),
                ("Mario", new List<bool> { true, false, true }, new List<double> { 9.5, 9.8 }),
                ("Elena", new List<bool> { true, true, true }, new List<double> { 7.0, 8.0 }),
                ("Carlos", new List<bool> { true, true, true }, new List<double> { 9.2, 9.7 })
            };
            var aprobados = alumnos8
                .Where(a => a.Asistencias.All(x => x) && a.Notas.Any(n => n > 9))
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", aprobados));

            /*
             Ejercicio 9: Detección de alumnos sobresalientes
             */
            var alumnos9 = new List<(string Nombre, List<double> Notas, bool Becado)>
            {
                ("Lucía", new List<double> { 8.5, 9.1, 9.0 }, true),
                ("Mario", new List<double> { 7.0, 6.5, 8.0 }, false),
                ("Elena", new List<double> { 9.5, 9.8, 10.0 }, true),
                ("Carlos", new List<double> { 6.0, 7.0, 6.5 }, true)
            };
            var promedioGeneral = alumnos9.Average(a => a.Notas.Average());
            var destacados = alumnos9
                .Where(a => a.Becado && a.Notas.Average() > promedioGeneral)
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", destacados));

            /*
             Ejercicio 10: Evaluación de participación activa
             */
            var alumnos10 = new List<(string Nombre, List<bool> Participaciones, bool TareasEntregadas)>
            {
                ("Lucía", new List<bool> { true, true, true, true, false }, true),
                ("Mario", new List<bool> { true, false, false, true, false }, true),
                ("Elena", new List<bool> { true, true, true, true, true }, false),
                ("Carlos", new List<bool> { true, true, false, true, true }, true)
            };
            var activos = alumnos10
                .Where(a => a.TareasEntregadas &&
                            ((double)a.Participaciones.Count(p => p) / a.Participaciones.Count) >= 0.8)
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", activos));

            /*
             Ejercicio 11: Identificación de alumnos con progreso académico
             */
            var alumnos11 = new List<(string Nombre, List<double> Trimestres, bool SolicitoTutoria)>
            {
                ("Lucía", new List<double> { 7.0, 8.0, 9.0 }, false),
                ("Mario", new List<double> { 6.5, 6.0, 7.0 }, true),
                ("Elena", new List<double> { 8.0, 8.5, 8.5 }, false),
                ("Carlos", new List<double> { 5.0, 6.5, 7.5 }, false)
            };
            var mejora = alumnos11
                .Where(a => !a.SolicitoTutoria && a.Trimestres[0] < a.Trimestres[1] && a.Trimestres[1] < a.Trimestres[2])
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", mejora));

            /*
             Ejercicio 12: Detección de alumnos con bajo rendimiento y alta asistencia
             */
            var alumnos12 = new List<(string Nombre, List<double> Notas, List<bool> Asistencias, bool RiesgoAcademico)>
            {
                ("Lucía", new List<double> { 4.5, 4.0, 5.0 }, new List<bool> { true, true, true, true, true }, true),
                ("Mario", new List<double> { 6.0, 5.5, 6.5 }, new List<bool> { true, false, true, true, true }, false),
                ("Elena", new List<double> { 3.0, 4.0, 4.5 }, new List<bool> { true, true, true, true, false }, true),
                ("Carlos", new List<double> { 4.0, 4.5, 4.8 }, new List<bool> { true, true, true, true, true }, true)
            };
            var filtrados = alumnos12
                .Where(a => a.RiesgoAcademico && a.Notas.Average() < 5 &&
                            ((double)a.Asistencias.Count(x => x) / a.Asistencias.Count) >= 0.9)
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", filtrados));

            /*
             Ejercicio 13: Alumnos con asistencia irregular y rendimiento alto
             */
            var alumnos13 = new List<(string Nombre, List<bool> Asistencias, List<double> Notas)>
            {
                ("Lucía", new List<bool> { true, true, false }, new List<double> { 9.0, 9.5 }),
                ("Mario", new List<bool> { true, true, true }, new List<double> { 8.0, 8.2 }),
                ("Elena", new List<bool> { false, false, false }, new List<double> { 9.1, 9.3 }),
                ("Carlos", new List<bool> { true, true, true }, new List<double> { 9.0, 9.0 })
            };
            var irregulares = alumnos13
                .Where(a => !a.Asistencias.All(x => x) && a.Notas.Average() > 8.5)
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", irregulares));

            /*
             Ejercicio 14: Alumnos con entregas recientes
             */
            var alumnos14 = new List<(string Nombre, List<DateTime> Entregas, bool Activo)>
            {
                ("Lucía", new List<DateTime> { DateTime.Now.AddDays(-10), DateTime.Now.AddDays(-30) }, true),
                ("Mario", new List<DateTime> { DateTime.Now.AddDays(-20) }, true),
                ("Elena", new List<DateTime> { DateTime.Now.AddDays(-5) }, false),
                ("Carlos", new List<DateTime> { DateTime.Now.AddDays(-2), DateTime.Now.AddDays(-1) }, true)
            };
            var recientes2 = alumnos14
                .Where(a => a.Activo && a.Entregas.Any(f => (DateTime.Now - f).TotalDays <= 15))
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", recientes2));

            /*
             Ejercicio 15: Ranking de alumnos por esfuerzo
             */
            var alumnos15 = new List<(string Nombre, int Tareas, int Participaciones)>
            {
                ("Lucía", 5, 10),
                ("Mario", 3, 7),
                ("Elena", 6, 4),
                ("Carlos", 2, 12)
            };
            var ranking = alumnos15
                .OrderByDescending(a => a.Tareas + a.Participaciones)
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", ranking));

            /*
             Ejercicio 16: Alumnos con tareas incompletas y bajo promedio
             */
            var alumnos16 = new List<(string Nombre, List<bool> Tareas, List<double> Notas)>
            {
                ("Lucía", new List<bool> { true, false, true }, new List<double> { 5.5, 6.0 }),
                ("Mario", new List<bool> { true, true, true }, new List<double> { 7.0, 8.0 }),
                ("Elena", new List<bool> { false, false, false}, new List<double> { 4.5, 5.0 }),
                ("Carlos", new List<bool> { true, true, false }, new List<double> { 6.5, 6.0 })
            };
            var incompletos = alumnos16
                .Where(a => !a.Tareas.All(t => t) && a.Notas.Average() < 6)
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", incompletos));

            /*
             Ejercicio 17: Alumnos con entregas en días festivos
             */
            var alumnos17 = new List<(string Nombre, List<DateTime> Entregas)>
            {
                ("Lucía", new List<DateTime> { new DateTime(2025, 10, 4), new DateTime(2025, 10, 6) }),
                ("Mario", new List<DateTime> { new DateTime(2025, 10, 2) }),
                ("Elena", new List<DateTime> { new DateTime(2025, 10, 5) }),
                ("Carlos", new List<DateTime> { new DateTime(2025, 10, 3) })
            };
            var festivos = alumnos17
                .Where(a => a.Entregas.Any(f => f.DayOfWeek == DayOfWeek.Saturday || f.DayOfWeek == DayOfWeek.Sunday))
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", festivos));

            /*
             Ejercicio 18: Alumnos con rendimiento equilibrado
             */
            var alumnos18 = new List<(string Nombre, List<double> Notas)>
            {
                ("Lucía", new List<double> { 8.5, 8.7, 8.6 }),
                ("Mario", new List<double> { 7.0, 9.0, 6.5 }),
                ("Elena", new List<double> { 9.0, 9.5, 9.2 }),
                ("Carlos", new List<double> { 6.0, 7.5, 8.0 })
            };
            var equilibrados = alumnos18
                .Where(a => a.Notas.Max() - a.Notas.Min() <= 1)
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();
            Console.WriteLine(string.Join(", ", equilibrados));
            /*
             Ejercicio 19: Alumnos con rendimiento sobresaliente en todas las materias
             Dada una lista de alumnos con nombre y lista de notas por materia, obtén los nombres de los alumnos que tienen todas las notas superiores a 8.5. Si no hay ninguno, devuelve "0".
            */
            var alumnos19 = new List<(string Nombre, List<double> Notas)>
{
    ("Lucía", new List<double> { 9.0, 9.5, 8.6 }),
    ("Mario", new List<double> { 8.0, 8.7, 9.0 }),
    ("Elena", new List<double> { 9.1, 9.3, 9.2 }),
    ("Carlos", new List<double> { 7.5, 8.0, 8.5 })
};

            var sobresalientes = alumnos19
                .Where(a => a.Notas.All(n => n > 8.5))
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();

            Console.WriteLine(string.Join(", ", sobresalientes));

            /*
             Ejercicio 20: Alumnos con mejora significativa en la última evaluación
             Dada una lista de alumnos con nombre y lista de notas ordenadas cronológicamente, obtén los nombres de los alumnos cuya última nota sea al menos 2 puntos superior a la primera. Si no hay ninguno, devuelve "0".
            */
            var alumnos20 = new List<(string Nombre, List<double> Notas)>
{
    ("Lucía", new List<double> { 6.0, 7.5, 8.5 }),
    ("Mario", new List<double> { 7.0, 7.5, 8.0 }),
    ("Elena", new List<double> { 5.0, 6.0, 7.5 }),
    ("Carlos", new List<double> { 8.0, 8.5, 9.0 })
};

            var mejora = alumnos20
                .Where(a => a.Notas.Last() >= a.Notas.First() + 2)
                .Select(a => a.Nombre)
                .DefaultIfEmpty("0")
                .ToList();

            Console.WriteLine(string.Join(", ", mejora));
        }
    }
}
