package com.example.produitrenume

enum class CategorieProduit(val description: String, val tva: Double) {
    ELECTRONIQUE("Produits électroniques, tels que téléphones et ordinateurs", 20.00),
    VETEMENTS("Vêtements pour hommes, femmes et enfants", 5.50),
    ALIMENTATION("Produits alimentaires et boissons", 5.50),
    LIBRAIRIE("Livres, fournitures scolaires et autres articles de papeterie", 10.00),
    MAISON("Produits pour la maison et le jardin", 15.00)
}
data class Produit(val nom : String, val prix:Double, val categorie:CategorieProduit){

    fun CalculerPrixAvecTVA(prix: Double,categorie: CategorieProduit):String{
        val prixAvecTVA = prix + (prix * categorie.tva / 100)

        return "Prix avec TVA: $prixAvecTVA MAD"
    }
}

data class Panier(val produits:MutableList<Produit>){
    fun ajouterProduit(produit: Produit) {
        produits.add(produit)
        println("Le produit '${produit.nom}' a été ajouté avec succès.")
    }

    fun retirerProduit(produit: Produit){
        if(produits.contains(produit)){
            produits.remove(produit)
            println("Le produit '${produit.nom}' a été retiré avec succès.")

        }else{
            println("Le produit avec le nom '${produit.nom}' n'existe pas. Impossible de le retirer.")

        }

    }

    fun calculerTotalHT():String{
       val TotalHT=   produits.sumOf { it.prix }
        return  "Total prix HT: ${TotalHT} MAD"
    }

    fun calculerTotalTTC():String{
        val TotalTTC=   produits.sumOf { it.prix + (it.prix * it.categorie.tva /100)}
        return  "Total prix TTC: ${TotalTTC} MAD"
    }

    fun afficherProduits(){

        if(produits.isEmpty()){
            println("Aucun produit disponible.")

        }else{
            produits.forEach { produit ->
                println("Nom: ${produit.nom}, Categorie: ${produit.categorie.name}, Prix HT: ${produit.prix}, TVA: ${produit.categorie.tva}%")

            }
        }

    }



}

fun main() {
    val produits = listOf(
        Produit("Laptop", 1500.0, CategorieProduit.ELECTRONIQUE),
        Produit("Smartphone", 800.0, CategorieProduit.ELECTRONIQUE),
        Produit("Livre", 30.0, CategorieProduit.LIBRAIRIE),
        Produit("T-Shirt", 25.0, CategorieProduit.VETEMENTS),
        Produit("Table", 200.0, CategorieProduit.MAISON)
    )

    val panier = Panier(mutableListOf())

    panier.ajouterProduit(produits[0])
    panier.ajouterProduit(produits[1])
    panier.ajouterProduit(produits[2])

    println()
    println("Panier")
    panier.afficherProduits()

    println("Prix total")
    println(panier.calculerTotalHT())
    println(panier.calculerTotalTTC())


    println()
    panier.retirerProduit(produits[1])

    println()
    panier.afficherProduits()
}