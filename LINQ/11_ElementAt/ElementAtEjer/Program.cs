namespace ElementAtEjer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            /* 
            Ejercicio 1: Obtener el tercer número par de un array
             */
            int[] numeros = { 3, 8, 12, 5, 6, 9, 14 };
            var ejer1 = numeros.Where(n => n % 2 == 0).ElementAt(2);
            Console.WriteLine($"Ejer 1: {ejer1}");
            /*
             * Ejercicio 2: Obtener el segundo nombre ordenado alfabéticamente
            */
            List<string> nombres = new List<string> { "Carlos", "Ana", "Luis", "Bea" };
            var ejer2 = nombres.OrderBy(n => n).ElementAt(1);
            Console.WriteLine($"Ejer 2: {ejer2}");

            /*
            Ejercicio 3: Obtener el promedio del tercer grupo de notas
            */
            List<List<int>> grupos = new List<List<int>> {
    new List<int>{ 5, 6, 7 },
    new List<int>{ 8, 9 },
    new List<int>{ 4, 4, 6, 7 }
};

            var ejer3 = grupos.ElementAt(2).Average();
            Console.WriteLine($"Ejer 3: {ejer3}");
            /*
            Ejercicio 20: Obtener el nombre del segundo empleado con más horas trabajadas
             */

        List<Empleado20> empleados = new List<Empleado20> {
    new Empleado20{ Nombre = "Ana", Horas = new List<int>{ 8, 8, 8 } },
    new Empleado20{ Nombre = "Luis", Horas = new List<int>{ 9, 9, 9, 9 } },
    new Empleado20{ Nombre = "Bea", Horas = new List<int>{ 10, 10 } }
};
            var ejer20 = empleados
        .Select(e => new
        {
            e.Nombre,
            TotalHoras = e.Horas.Sum()
        })
        .OrderByDescending(e => e.TotalHoras)
        .ElementAt(1);
            Console.WriteLine($"Ejer 20: {ejer20.Nombre}");

            /*
            Ejercicio 26: Obtener el segundo departamento con mayor número de empleados mayores de 40 años
             */

            List<Departamento> departamentos = new List<Departamento> {
    new Departamento{ Nombre = "Ventas", Empleados = new List<Empleado>{ new Empleado{Edad=45}, new Empleado{Edad=38} } },
    new Departamento{ Nombre = "Marketing", Empleados = new List<Empleado>{ new Empleado{Edad=42}, new Empleado{Edad=41}, new Empleado{Edad=39} } },
    new Departamento{ Nombre = "IT", Empleados = new List<Empleado>{ new Empleado{Edad=50}, new Empleado{Edad=47}, new Empleado{Edad=43} } }
};
        var ejer26 = departamentos.Select(d => new
            {
                NombreDepartamento = d.Nombre,
                ConteoMayores40 = d.Empleados.Count(e => e.Edad > 40)
            })
            .OrderByDescending(d => d.ConteoMayores40)
            .Skip(1)
            .Take(1)
            .Select(d => d.NombreDepartamento)
            .FirstOrDefault(); 
            Console.WriteLine($"Ejer 26: {ejer26}");
            /*
             Ejercicio 27: Promedio de notas y detección de sobresalientes
    Dada una lista de alumnos con sus calificaciones, encuentra el segundo alumno (usando ElementAt) que tiene un promedio superior a 8 y al menos una nota perfecta (10). 
             */
            var alumnos27 = new List<(string Nombre, List<double> Notas)>
{
    ("Sofía", new List<double> { 9, 10, 8.5 }),
    ("Pedro", new List<double> { 7, 6.5, 8 }),
    ("Elena", new List<double> { 10, 10, 10 }),
    ("Tomás", new List<double> { 8.5, 9, 10 }),
    ("Raúl", new List<double> { 6, 7, 8 })
};
            var ejer27 = alumnos27.Where(alumno => alumno.Notas.Average() > 8 && alumno.Notas.Contains(10)).ElementAt(1);
            Console.WriteLine($"Ejer 27: {ejer27.Nombre}");
            /*
             Ejercicio 28: Alumnos con tareas entregadas en días pares
En una lista de alumnos con fechas de entrega de tareas, encuentra el primer alumno que ha entregado tareas solo en días pares del mes. 
             */
            var alumnos = new List<(string Nombre, List<DateTime> Entregas)>
{
    ("Mario", new List<DateTime> { new DateTime(2025, 10, 2), new DateTime(2025, 10, 4), new DateTime(2025, 10, 6) }),
    ("Clara", new List<DateTime> { new DateTime(2025, 10, 1), new DateTime(2025, 10, 3) }),
    ("Jorge", new List<DateTime> { new DateTime(2025, 10, 8), new DateTime(2025, 10, 10) }),
    ("Sara", new List<DateTime> { new DateTime(2025, 10, 5), new DateTime(2025, 10, 7) })
};
            var ejer28 = alumnos.Where(alumno => alumno.Entregas.All(fecha => fecha.Day % 2 == 0)).ElementAt(0);
            if (ejer28 != default)
            {
                Console.WriteLine($"Ejer 28: {ejer28.Nombre}");
            }
            else
            {
                Console.WriteLine("Ejer 28: No se encontró ningún alumno que cumpla los criterios.");
            }
            /*
             Ejercicio 29: Control de participación en clase
Dado un listado de alumnos con número de intervenciones en clase por semana, encuentra el segundo alumno que nunca participó y cuya suma de participaciones es 0. 
             */
            var alumnos29 = new List<(string Nombre, List<int> Participaciones)>
{
    ("Andrés", new List<int> { 0, 0, 0 }),
    ("Bea", new List<int> { 1, 0, 2 }),
    ("Carmen", new List<int>()), // Lista vacía
    ("David", new List<int> { 0, 0 }),
    ("Eva", new List<int> { 3, 1, 0 })
};
          var ejer29 = alumnos29.Where(alumno => alumno.Participaciones.Sum() == 0).OrderBy(n => n.Nombre).Select(n => n.Nombre).Skip(1).Take(1);
            Console.WriteLine($"Ejer 29: {ejer29}");
            /*
             Ejercicio 30: Análisis de entregas y notas extremas
            Dado un listado de alumnos con fechas de entrega y notas, encuentra el primer alumno que ha entregado tareas en más de 3 días distintos, tiene al menos una nota menor que 5 y una nota mayor que 9.
             */
            var alumnos30 = new List<(string Nombre, List<DateTime> Entregas, List<double> Notas)>
{
    ("Sergio", new List<DateTime> { new DateTime(2025, 10, 1), new DateTime(2025, 10, 2), new DateTime(2025, 10, 3), new DateTime(2025, 10, 4) }, new List<double> { 4.5, 9.5 }),
    ("Bea", new List<DateTime> { new DateTime(2025, 10, 1), new DateTime(2025, 10, 2) }, new List<double> { 6, 7 }),
    ("Raúl", new List<DateTime> { new DateTime(2025, 10, 1), new DateTime(2025, 10, 2), new DateTime(2025, 10, 3), new DateTime(2025, 10, 4), new DateTime(2025, 10, 5) }, new List<double> { 3, 10 }),
    ("Inés", new List<DateTime> { new DateTime(2025, 10, 1), new DateTime(2025, 10, 2), new DateTime(2025, 10, 3) }, new List<double> { 5, 6, 7 })
};

            var ejer30 = alumnos30.Where(alumno =>
                alumno.Entregas.Select(fecha => fecha.Date).Distinct().Count() > 3 &&
                alumno.Notas.Any(nota => nota < 5) &&
                alumno.Notas.Any(nota => nota > 9)
            ).ElementAt(0);
            if (ejer30 != default)
            {
                Console.WriteLine($"Ejer 30: {ejer30.Nombre}");
            }
            else
            {
                Console.WriteLine("Ejer 30: No se encontró ningún alumno que cumpla los criterios.");
            }

        }
    }
    class Empleado { public int Edad; }
    class Departamento { public string Nombre; public List<Empleado> Empleados; }
    class Empleado20 { public string Nombre; public List<int> Horas; }

}
