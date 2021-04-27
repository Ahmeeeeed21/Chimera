<?php

namespace App\Form;

use App\Entity\Ingredient;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Vich\UploaderBundle\Form\Type\VichImageType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
class IngredientType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom', TextType::class, ['attr' => ['placeholder' => 'nom', 'class' => 'form-control pname'],])

            ->add('image_File', FileType::class,['attr' =>['placeholder' => 'Select Image']])
            ->add('calorie')
            ->add('unite',ChoiceType::class,[
                'choices' => [

                    'Unite' => [
                        'Please select unite'=>'vide',
                        'g' => '1 gramme',
                        '100g' => '100 gramme',
                        '1cl' => '1 cl',
                        '100cl' => '100 cl',
                        'Paquet' => '1 paquet',
                        'CuillereàSoupe' =>'1 Càs',
                    ],

                ],


            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Ingredient::class,
        ]);
    }
}
