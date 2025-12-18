using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Json01.Modelos
{
    public class Casa
    {
        public string id { get; set; }
        public string name { get; set; }
        public string city { get; set; }
        public string state { get; set; }
        public string photo { get; set; }
        public int availableUnits { get; set; }
        public bool wifi { get; set; }
        public bool laundry { get; set; }

    }
}
