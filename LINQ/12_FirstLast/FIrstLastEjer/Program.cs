namespace FIrstLastEjer
{
    internal class Program
    {
        static void Main(string[] args)
        {
            /*
             * 1. Nombres que empiezan por vocal
            Dado un array de nombres, obtener el primer y último nombre que empieza por vocal.
            Mostrar cuántos nombres hay, si todos tienen más de 3 letras, y si alguno contiene la letra "z".
             */
            List<string> nombres = new List<string> { "Ana", "Luis", "Oscar", "Elena", "Carlos", "Ignacio", "Zoe" };
            var vocales = new[] { 'A', 'E', 'I', 'O', 'U' };
var nombresPorVocal = nombres
                .Where(nombre => vocales.Contains(char.ToUpper(nombre[0])))
                .ToList();
            var primerNombre = nombresPorVocal.FirstOrDefault();
            var ultimoNombre = nombresPorVocal.LastOrDefault();
            Console.WriteLine($"Número total de nombres: {nombres.Count}");
            Console.WriteLine($"Todos los nombres tienen más de 3 letras: {nombres.All(nombre => nombre.Length > 3)}");
            Console.WriteLine($"Algún nombre contiene la letra 'z': {nombres.Any(nombre => nombre.ToLower().Contains('z'))}");
            Console.WriteLine($"Primer nombre: {primerNombre}");
            Console.WriteLine($"Último nombre: {ultimoNombre}");
            /*
             19. Filtrado de reservas de hotel
Dada una lista de reservas de hotel con fecha de entrada, fecha de salida y estado de confirmación, obtener: 
La primera y última reserva confirmada que se realiza en temporada alta (junio a agosto). Calcular el número total de reservas, cuántas están confirmadas, si todas las reservas duran más de 2 días, y si alguna ocurre en fin de semana. Mostrar también la reserva central por fecha de entrada.
             */
            var reservas = new List<Reserva>
            {
                new Reserva { Entrada = new DateTime(2025, 6, 10), Salida = new DateTime(2025, 6, 15), Confirmada = true },
                new Reserva { Entrada = new DateTime(2025, 7, 5), Salida = new DateTime(2025, 7, 8), Confirmada = false },
                new Reserva { Entrada = new DateTime(2025, 8, 1), Salida = new DateTime(2025, 8, 5), Confirmada = true },
                new Reserva { Entrada = new DateTime(2025, 9, 10), Salida = new DateTime(2025, 9, 12), Confirmada = true },
                new Reserva { Entrada = new DateTime(2025, 6, 20), Salida = new DateTime(2025, 6, 23), Confirmada = true }
            };
            reservas = reservas.OrderBy(r => r.Entrada).ToList();
            var reservasTemporadaAlta = reservas
                .Where(r => r.Confirmada && r.Entrada.Month >= 6 && r.Entrada.Month <= 8)
                .ToList();
            var primeraReserva = reservasTemporadaAlta.FirstOrDefault();
            var ultimaReserva = reservasTemporadaAlta.LastOrDefault();
            Console.WriteLine($"Primera reserva en temporada alta: Entrada {primeraReserva?.Entrada.ToShortDateString()}, Salida {primeraReserva?.Salida.ToShortDateString()}");
            Console.WriteLine($"Última reserva en temporada alta: Entrada {ultimaReserva?.Entrada.ToShortDateString()}, Salida {ultimaReserva?.Salida.ToShortDateString()}");
            Console.WriteLine($"Número total de reservas: {reservas.Count}");
            Console.WriteLine($"Número de reservas confirmadas: {reservas.Count(r => r.Confirmada)}");
            Console.WriteLine($"Todas las reservas duran más de 2 días: {reservas.All(r => (r.Salida - r.Entrada).TotalDays > 2)}");
            Console.WriteLine($"Alguna reserva ocurre en fin de semana: {reservas.Any(r => r.Entrada.DayOfWeek == DayOfWeek.Saturday || r.Entrada.DayOfWeek == DayOfWeek.Sunday)}");
            var reservaCentral = reservas[reservas.Count / 2];
            Console.WriteLine($"Reserva central por fecha de entrada: Entrada {reservaCentral.Entrada.ToShortDateString()}, Salida {reservaCentral.Salida.ToShortDateString()}");
            /*
             20. Evaluación de tareas de proyecto
     Dada una lista de tareas con fecha de entrega, estado de finalización y prioridad, obtener: 
     La primera y última tarea completada con prioridad alta. 
     Verificar si todas las tareas están completadas o son de baja prioridad, 
     Verificar si alguna se entrega en fin de semana, 
     Calcular el número total de tareas, el número de tareas con prioridad alta.
     Calcular la media de días restantes hasta la entrega (solo para tareas no completadas).

             */
            var tareas = new List<Tarea>
         {
             new Tarea { Nombre = "Diseño", FechaEntrega = DateTime.Today.AddDays(3), Completada = true, Prioridad = "Alta" },
             new Tarea { Nombre = "Backend", FechaEntrega = DateTime.Today.AddDays(7), Completada = false, Prioridad = "Alta" },
             new Tarea { Nombre = "Frontend", FechaEntrega = DateTime.Today.AddDays(5), Completada = true, Prioridad = "Media" },
             new Tarea { Nombre = "Testing", FechaEntrega = DateTime.Today.AddDays(2), Completada = true, Prioridad = "Alta" },
             new Tarea { Nombre = "Documentación", FechaEntrega = DateTime.Today.AddDays(10), Completada = false, Prioridad = "Baja" }
         };
            Console.WriteLine(
                $"Primera tarea completada con prioridad alta: " +
                $"{tareas.Where(t => t.Completada && t.Prioridad == "Alta").FirstOrDefault()?.Nombre}");
            Console.WriteLine(
                $"Última tarea completada con prioridad alta: " +
                $"{tareas.Where(t => t.Completada && t.Prioridad == "Alta").LastOrDefault()?.Nombre}");
            Console.WriteLine(
                $"Todas las tareas están completadas o son de baja prioridad: " +
                $"{tareas.All(t => t.Completada || t.Prioridad == "Baja")}");
            Console.WriteLine(
                $"Alguna tarea se entrega en fin de semana: " +
                $"{tareas.Any(t => t.FechaEntrega.DayOfWeek == DayOfWeek.Saturday || t.FechaEntrega.DayOfWeek == DayOfWeek.Sunday)}");
            Console.WriteLine(
                $"Número total de tareas: {tareas.Count}");
            Console.WriteLine(
                $"Número de tareas con prioridad alta: " +
                $"{tareas.Count(t => t.Prioridad == "Alta")}");
            var diasRestantes = tareas
                .Where(t => !t.Completada)
                .Select(t => (t.FechaEntrega - DateTime.Today).TotalDays);
            Console.WriteLine(
                $"Media de días restantes hasta la entrega (tareas no completadas): " +
                $"{(diasRestantes.Any() ? diasRestantes.Average() : 0)}");
            /*
             21. Analisis de sesiones de usuario
Dada una lista de sesiones de usuario con fecha de inicio, duración en minutos y si fue exitosa, 
Obtener la primera y última sesión exitosa que duró más de 30 minutos. 
Verificar si todas las sesiones exitosas ocurrieron en días laborables, 
Verificar si alguna sesión fallida duró más de 60 minutos
Calcular el promedio de duración, la sesión central por fecha, y el total de minutos exitosos. 
             */
        var sesiones = new List<Sesion>
    {
        new Sesion { Inicio = new DateTime(2025, 10, 6, 9, 0, 0), DuracionMinutos = 45, Exitosa = true },
        new Sesion { Inicio = new DateTime(2025, 10, 7, 10, 0, 0), DuracionMinutos = 20, Exitosa = false },
        new Sesion { Inicio = new DateTime(2025, 10, 8, 11, 0, 0), DuracionMinutos = 90, Exitosa = true },
        new Sesion { Inicio = new DateTime(2025, 10, 11, 12, 0, 0), DuracionMinutos = 65, Exitosa = false },
        new Sesion { Inicio = new DateTime(2025, 10, 9, 13, 0, 0), DuracionMinutos = 35, Exitosa = true }
    };
            sesiones = sesiones.OrderBy(s => s.Inicio).ToList();
        var sesionesExitosasLargas = sesiones
            .Where(s => s.Exitosa && s.DuracionMinutos > 30)
            .ToList();
        var primeraSesion = sesionesExitosasLargas.FirstOrDefault();
        var ultimaSesion = sesionesExitosasLargas.LastOrDefault();
        Console.WriteLine($"Primera sesión exitosa >30 min: Inicio {primeraSesion?.Inicio}, Duración {primeraSesion?.DuracionMinutos} min");
        Console.WriteLine($"Última sesión exitosa >30 min: Inicio {ultimaSesion?.Inicio}, Duración {ultimaSesion?.DuracionMinutos} min");
        Console.WriteLine($"Todas las sesiones exitosas en días laborables: " +
            $"{sesionesExitosasLargas.All(s => s.Inicio.DayOfWeek != DayOfWeek.Saturday && s.Inicio.DayOfWeek != DayOfWeek.Sunday)}");
        Console.WriteLine($"Alguna sesión fallida >60 min: " +
            $"{sesiones.Any(s => !s.Exitosa && s.DuracionMinutos > 60)}");
        Console.WriteLine($"Promedio de duración de sesiones: " +
            $"{sesiones.Average(s => s.DuracionMinutos)} min");
        var sesionCentral = sesiones[sesiones.Count / 2];
        Console.WriteLine($"Sesión central por fecha: Inicio {sesionCentral.Inicio}, Duración {sesionCentral.DuracionMinutos} min");
        Console.WriteLine($"Total de minutos exitosos: " +
            $"{sesiones.Where(s => s.Exitosa).Sum(s => s.DuracionMinutos)} min");


        }
}
    class Sesion
    {
        public DateTime Inicio { get; set; }
        public int DuracionMinutos { get; set; }
        public bool Exitosa { get; set; }
    }
    class Tarea
    {
        public string Nombre { get; set; }
        public DateTime FechaEntrega { get; set; }
        public bool Completada { get; set; }
        public string Prioridad { get; set; } // "Alta", "Media", "Baja"
     }

    class Reserva
    {
        public DateTime Entrada { get; set; }
        public DateTime Salida { get; set; }
        public bool Confirmada { get; set; }
    }

}
