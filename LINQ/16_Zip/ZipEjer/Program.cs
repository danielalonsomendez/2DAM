using Microsoft.Win32;
using System.Linq;
using System.Security.Claims;

namespace ZipEjer
{
    internal class Program
    {
        class Alumno
        {
            public string Nombre { get; set; }
            public List<int> NotasTrimestres { get; set; }
        }

        public class Proyecto
        {
    public string Alumno { get; set; }
        public DateTime FechaEntrega { get; set; }
}

        static void Main(string[] args)
        {
            /*
      * EJERCICIO 1 – ZIP
      * En una lista de proyectos entregados, obtener:
      * 1. Los que fueron entregados en días laborables (lunes a viernes).
      * 2. Ordenar los proyectos por fecha.
      * 3. Mostrar el nombre del alumno y la fecha.
      * 4. Calcular el promedio de días entre entregas.
      */

            int anioActual = DateTime.Now.Year;

            var proyectos = new List<Proyecto> {
            new Proyecto { Alumno = "Ana",   FechaEntrega = new DateTime(anioActual, 12, 10) },
            new Proyecto { Alumno = "Luis",  FechaEntrega = new DateTime(anioActual, 12, 12) },
            new Proyecto { Alumno = "Marta", FechaEntrega = new DateTime(anioActual, 12, 15) },
            new Proyecto { Alumno = "Pedro", FechaEntrega = new DateTime(anioActual, 12, 18) }
        };


            var proyectosLaborables = proyectos
                .Where(p =>
                    p.FechaEntrega.DayOfWeek != DayOfWeek.Saturday &&
                    p.FechaEntrega.DayOfWeek != DayOfWeek.Sunday)
                .OrderBy(p => p.FechaEntrega)
                .ToList();

            Console.WriteLine("Proyectos entregados en días laborables:");
            foreach (var p in proyectosLaborables)
            {
                Console.WriteLine($"{p.Alumno} - {p.FechaEntrega:dd/MM/yyyy}");
            }

            var diferenciasDias = proyectosLaborables
                .Select(p => p.FechaEntrega)
                .Zip(
                    proyectosLaborables.Skip(1).Select(p => p.FechaEntrega),
                    (fechaActual, fechaSiguiente) => (fechaSiguiente - fechaActual).TotalDays
                );

            double promedioDias = diferenciasDias.Any()
                ? diferenciasDias.Average()
                : 0;

            Console.WriteLine($"\nPromedio de días entre entregas: {promedioDias:F2}");

            /*
        * EJERCICIO 2 – ZIP
        * Cada alumno tiene una lista de notas por trimestre.
        * Se desea:
        * 1. Calcular la diferencia de nota entre trimestres consecutivos.
        * 2. Determinar el rendimiento del alumno según las diferencias:
        *    - Ascendente: todas las diferencias son positivas o cero.
        *    - Descendente: todas las diferencias son negativas o cero.
        *    - Irregular: cualquier otro caso.
        */

            var alumnos = new List<Alumno> {
            new Alumno { Nombre = "Ana",   NotasTrimestres = new List<int>{ 7, 8, 9 } },
            new Alumno { Nombre = "Luis",  NotasTrimestres = new List<int>{ 6, 5, 7 } },
            new Alumno { Nombre = "Marta", NotasTrimestres = new List<int>{ 9, 9, 8 } }
        };


            foreach (var alumno in alumnos)
            {
        
                var diferencias = alumno.NotasTrimestres
                    .Zip(
                        alumno.NotasTrimestres.Skip(1),
                        (notaActual, notaSiguiente) => notaSiguiente - notaActual
                    )
                    .ToList();

              
                string rendimiento;

                if (diferencias.All(d => d >= 0))
                {
                    rendimiento = "Ascendente";
                }
                else if (diferencias.All(d => d <= 0))
                {
                    rendimiento = "Descendente";
                }
                else
                {
                    rendimiento = "Irregular";
                }

               
                Console.WriteLine($"Alumno: {alumno.Nombre}");
                Console.WriteLine($"Diferencias entre trimestres: {string.Join(", ", diferencias)}");
                Console.WriteLine($"Rendimiento: {rendimiento}");
                Console.WriteLine(new string('-', 40));
            }


            /*
       * EJERCICIO 3 – ZIP
       * En una lista de registros de asistencia (presente/ausente por día),
       * se desea calcular cuántas veces un alumno estuvo presente
       * dos días seguidos.
       */

            int anioActual = DateTime.Now.Year;

            var asistencias = new List<Registro>
        {
            new Registro { Nombre = "Ana",  Fecha = new DateTime(anioActual, 11, 10), Presente = true },
            new Registro { Nombre = "Ana",  Fecha = new DateTime(anioActual, 11, 11), Presente = true }, // Par 1 Ana
            new Registro { Nombre = "Luis", Fecha = new DateTime(anioActual, 11, 10), Presente = true },
            new Registro { Nombre = "Luis", Fecha = new DateTime(anioActual, 11, 11), Presente = false },
            new Registro { Nombre = "Ana",  Fecha = new DateTime(anioActual, 11, 12), Presente = true }, // Par 2 Ana (11 y 12)
            new Registro { Nombre = "Luis", Fecha = new DateTime(anioActual, 11, 12), Presente = true },
            new Registro { Nombre = "Luis", Fecha = new DateTime(anioActual, 11, 13), Presente = true }  // Par 1 Luis
        };

   
            var resultado = asistencias
                .GroupBy(r => r.Nombre)
                .Select(grupo =>
                {
                    var registrosOrdenados = grupo
                        .OrderBy(r => r.Fecha)
                        .ToList();

                    
                    int paresConsecutivos = registrosOrdenados
                        .Zip(
                            registrosOrdenados.Skip(1),
                            (actual, siguiente) =>
                                actual.Presente &&
                                siguiente.Presente &&
                                (siguiente.Fecha - actual.Fecha).Days == 1
                        )
                        .Count(x => x);

                    return new
                    {
                        Alumno = grupo.Key,
                        DiasConsecutivosPresente = paresConsecutivos
                    };
                });

      
            foreach (var r in resultado)
            {
                Console.WriteLine($"{r.Alumno}: {r.DiasConsecutivosPresente} veces presente dos días seguidos");
            }
        }
        class Registro
        {
            public string Nombre { get; set; }
            public DateTime Fecha { get; set; }
            public bool Presente { get; set; }
        }

    }
}
