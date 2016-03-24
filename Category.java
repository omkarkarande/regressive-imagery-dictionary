public class Category {
        private String primary;
        private String secondary;
        private String tertiary;

        public Category(){
        }

        public Category(String primary){
                this.primary = primary;
        }

        public Category(String primary, String secondary){
                this.primary = primary;
                this.secondary = secondary;
        }

        public Category(String primary, String secondary, String tertiary){
                this.primary = primary;
                this.secondary = secondary;
                this.tertiary = tertiary;
        }

        public void setPrimary(String primary){
                this.primary = primary;
        }
        public String getPrimary(){
                return this.primary;
        }

        public void setSecondary(String secondary){
                this.secondary = secondary;
        }
        public String getSecondary(){
                return this.secondary;
        }

        public void setTertiary(String tertiary){
                this.tertiary = tertiary;
        }
        public String getTertiary(){
                return this.tertiary;
        }

        public String toString(){
                if (this.primary != null && this.secondary != null && this.tertiary != null) {
                        return this.primary + ":" + this.secondary + ":" + this.tertiary;
                }
                if (this.primary != null && this.secondary != null) {
                        return this.primary + ":" + this.secondary;
                }
                if (this.primary != null) {
                        return this.primary;
                }
                return null;
        }
}
