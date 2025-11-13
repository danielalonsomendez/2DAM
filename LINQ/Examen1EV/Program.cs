namespace Evaluacion1_DanielAlonso
{
    internal class Program
    {
        static void Main(string[] args)
        {
            /*
			Ejercicio 1: Dado un listado de alumnos con su historial de asistencia(true / false por día), mostrar los nombres de aquellos que asistieron todos los días. 
			*/

            var alumnos1 = new[]
{
    new { Nombre = "Lucía", Asistencia = new[] { true, true, true, true } },
    new { Nombre = "Carlos", Asistencia = new[] { true, false, true, true } },
    new { Nombre = "Ana", Asistencia = new[] { true, true, true, true } }
};
            var alumnosAsistenciaNombre = alumnos1
                .Where(a => !a.Asistencia.Any(a => a == false))
                .Select(a => a.Nombre)
                .DefaultIfEmpty(null)
                .ToList();
            Console.WriteLine("Ejercicio 1: " + String.Join(", ", alumnosAsistenciaNombre));
            /*
			 Ejercicio 2: Alumnos con rendimiento equilibrado
			Dada una lista de alumnos con nombre y lista de notas, obtén los nombres de los alumnos 
            cuyo mínimo y máximo de notas no difieren en más de 1 punto, indicando que 
            tienen un rendimiento estable. Si no hay ninguno, devuelve "0".
			 */
            var alumnos = new List<(string Nombre, List<double> Notas)>
{
    ("Lucía", new List<double> { 8.5, 8.7, 8.6 }),
    ("Mario", new List<double> { 7.0, 9.0, 6.5 }),
    ("Elena", new List<double> { 9.0, 9.5, 9.2 }),
    ("Carlos", new List<double> { 6.0, 7.5, 8.0 })
};

            var alumnosDiferenciaNotas1p = alumnos
                .Where(a => (a.Notas.Max() - a.Notas.Min() < 1))
                .DefaultIfEmpty() // No deja hacer .DefaultIfEmpty(0) debido a que es un objeto
                .ToList();

            if (alumnosDiferenciaNotas1p.Count == 0)
            {
                Console.WriteLine("Ejercicio 2: 0");
            }
            else
            {
                Console.WriteLine("Ejercicio 2: Alumnos con rendimiento estable");
                for (int i = 0; i < alumnosDiferenciaNotas1p.Count; i++)
                {
                    Console.WriteLine(" - " + alumnosDiferenciaNotas1p[i].Nombre.ToString());
                }
            }
            /*
		 Ejercicio 3: Filtrar los alumnos que hayan entregado al menos una tarea en los últimos 7 días.
			*/
            var hoy = DateTime.Today;
            var alumnos3 = new[]
            {
    new { Nombre = "Pedro", Entregas = new[] { hoy.AddDays(-2), hoy.AddDays(-10) } },
    new { Nombre = "Sara", Entregas = new[] { hoy.AddDays(-1), hoy.AddDays(-3) } },
    new { Nombre = "Tomás", Entregas = new[] { hoy.AddDays(-8) } }
};
            var alumnosEntregas7Dias = alumnos3
                  .Where(a => a.Entregas.Any(a => hoy.AddDays(-7) <= a))
                  .DefaultIfEmpty(null)
                  .ToList();
            Console.WriteLine("Ejercicio 3:");
            for (int i = 0; i < alumnosEntregas7Dias.Count; i++)
            {
                Console.WriteLine(" - " + alumnosEntregas7Dias[i].Nombre.ToString());
            }
            /*
			 Ejercicio 4: Dado un array de eventos con fecha y si son importantes (bool), selecciona el segundo evento importante que ocurrirá en los próximos 30 días. 
			 */

            var eventos = new[]
            {
    new Evento { Titulo = "Reunión", Fecha = DateTime.Today.AddDays(10), Importante = true },
    new Evento { Titulo = "Entrega", Fecha = DateTime.Today.AddDays(5), Importante = false },
    new Evento { Titulo = "Conferencia", Fecha = DateTime.Today.AddDays(20), Importante = true },
    new Evento { Titulo = "Fiesta", Fecha = DateTime.Today.AddDays(25), Importante = true },
    new Evento { Titulo = "Viaje", Fecha = DateTime.Today.AddDays(40), Importante = true }
};

            var segundoEventoImportante30d = eventos.
                    Where(e => e.Importante && DateTime.Now.AddDays(30) >= e.Fecha).DefaultIfEmpty(null)
                    .ElementAt(1);
            Console.WriteLine("Ejercicio 4: " + segundoEventoImportante30d.Titulo + " " + segundoEventoImportante30d.Fecha + " " + segundoEventoImportante30d.Importante);
            /*
			 Ejercicio 5: Mostrar los nombres de los alumnos que no entregaron ninguna tarea.;
			*/
            var alumnos5 = new[]
{
    new { Nombre = "Iván", Entregas = new string[] { } },
    new { Nombre = "Clara", Entregas = new[] { "Tarea1" } },
    new { Nombre = "Nuria", Entregas = new string[] { } }
};
            var alumnosSinTareas = alumnos5.Where(a => a.Entregas.Length == 0).Select(a => a.Nombre).DefaultIfEmpty(null).ToList();
            Console.WriteLine("Ejercicio 5: " + String.Join(", ", alumnosSinTareas));
            /*
Ejercicio 6: Dada una lista de productos con nombre, cantidad en stock y si están activos, obtén el primer producto que:
Está activo.
Tiene un stock mayor al promedio.
Su nombre contiene la letra "A" (ignorar mayúsculas). 
			 */


            var productos = new List<Producto>
{
    new Producto { Nombre = "Arroz", Stock = 50, Activo = true },
    new Producto { Nombre = "Pan", Stock = 30, Activo = false },
    new Producto { Nombre = "Aceite", Stock = 70, Activo = true },
    new Producto { Nombre = "Sal", Stock = 20, Activo = true },
    new Producto { Nombre = "Azúcar", Stock = 60, Activo = true }
};

            var productosStockPromedio = productos.Average(p => p.Stock);
            var productoActivoMediaconA = productos
                .Where(p => p.Activo && productosStockPromedio < p.Stock && p.Nombre.Contains("A", StringComparison.OrdinalIgnoreCase)).DefaultIfEmpty(null)
                .ElementAt(0);
            Console.WriteLine("Ejercicio 6: " + productoActivoMediaconA.Nombre + " " + productoActivoMediaconA.Stock + " " + productoActivoMediaconA.Activo);
            /*
			 Ejercicio 7: Mostrar los alumnos que aprobaron todas sus asignaturas.
			*/
            var alumnos7 = new[]
{
    new { Nombre = "Andrea", Notas = new[] { 6, 7, 8 } },
    new { Nombre = "Pablo", Notas = new[] { 4, 5, 6 } },
    new { Nombre = "Lucía", Notas = new[] { 5, 5, 5 } }
};
            var alumnosAprobaronTodas = alumnos7
                .Where(a => !a.Notas.Any(a => a < 5)).
                ToList();
            Console.WriteLine("Ejercicio 7:");
            for (int i = 0; i < alumnosAprobaronTodas.Count; i++)
            {
                Console.WriteLine(" - " + alumnosAprobaronTodas[i].Nombre.ToString());
            }
            /*
			 Ejercicio 8: Alumnos con entregas en días festivos
Dada una lista de alumnos con nombre y lista de fechas de entrega de tareas, obtén los nombres de los alumnos que han 
            entregado al menos una tarea en fin de semana (sábado o domingo). Si no hay ninguno, devuelve "0".
			 */
            var alumnosEj8 = new List<(string Nombre, List<DateTime> Entregas)>
{
    ("Lucía", new List<DateTime> { new DateTime(2025, 10, 4), new DateTime(2025, 10, 6) }), // sábado
	("Mario", new List<DateTime> { new DateTime(2025, 10, 2) }),
    ("Elena", new List<DateTime> { new DateTime(2025, 10, 5) }), // domingo
	("Carlos", new List<DateTime> { new DateTime(2025, 10, 3) })
};
            var alumnosFindeSemana = alumnosEj8
                   .Where(a => a.Entregas.Any(a => a.DayOfWeek == DayOfWeek.Saturday || a.DayOfWeek == DayOfWeek.Sunday))
                   .DefaultIfEmpty()  // No deja hacer .DefaultIfEmpty(0) debido a que es un objeto
                   .ToList();
            if (alumnosFindeSemana.Count == 0)
            {
                Console.WriteLine("Ejercicio 8: 0");
            }
            else
            {
                Console.WriteLine("Ejercicio 8:");
                for (int i = 0; i < alumnosFindeSemana.Count; i++)
                {
                    Console.WriteLine(" - " + alumnosFindeSemana[i].Nombre.ToString());
                }
            }
            /*            
			 Ejercicio 9:Dado un historial de fechas de asistencia, mostrar los alumnos que faltaron al menos un viernes.
			 */
            var alumnos9 = new[]
{
    new { Nombre = "Alberto", Asistencias = new[] { new { Fecha = new DateTime(2025, 10, 3), Presente = true }, new { Fecha = new DateTime(2025, 10, 10), Presente = false } } },
    new { Nombre = "Beatriz", Asistencias = new[] { new { Fecha = new DateTime(2025, 10, 3), Presente = true }, new { Fecha = new DateTime(2025, 10, 10), Presente = true } } },
    new { Nombre = "César", Asistencias = new[] { new { Fecha = new DateTime(2025, 10, 3), Presente = false } } }
};


            var alumnosFaltasViernes = alumnos9
                .Where(a => a.Asistencias.Any(f => f.Fecha.DayOfWeek == DayOfWeek.Friday && !f.Presente))
                .DefaultIfEmpty(null)
                .ToList();
            Console.WriteLine("Ejercicio 9:");
            for (int i = 0; i < alumnosFaltasViernes.Count; i++)
            {
                Console.WriteLine(" - " + alumnosFaltasViernes[i].Nombre.ToString());
            }
            /*
			 Ejercicio 10: Mostrar los alumnos cuya nota media está entre 6 y 8 (inclusive).
			*/
            var alumnosEj10 = new[]
{
    new { Nombre = "Gabriela", Notas = new[] { 6.0, 7.0, 8.0 } },
    new { Nombre = "Hugo", Notas = new[] { 9.0, 9.5, 10.0 } },
    new { Nombre = "Irene", Notas = new[] { 5.0, 6.0, 6.5 } }
};

            var alumnosNotas = alumnosEj10.Where(a => a.Notas.Average() >= 6 && a.Notas.Average() <= 8).ToList();
            Console.WriteLine("Ejercicio 10:");
            for (int i = 0; i < alumnosNotas.Count; i++)
            {
                Console.WriteLine(" - " + alumnosNotas[i].Nombre.ToString());
            }
        }

    }
    class Producto
    {
        public string Nombre { get; set; }
        public int Stock { get; set; }
        public bool Activo { get; set; }
    }
    class Evento
    {
        public string Titulo { get; set; }
        public DateTime Fecha { get; set; }
        public bool Importante { get; set; }
    }

}
